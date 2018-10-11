package test.multi_thread_downloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import test.utils.IOUtils;
import test.utils.NetUtil;

/**
 * Created by mac on 2017/9/28.
 */

class MultiThreadDownloader {
    private static final String TEMP_FILE_PRE_FIX = "tempFile";
    private static final String TEMP_FILE_SUF_FIX = ".temp";

    private String urlStr;
    private String dirStr;
    private String filename;
    private long fileSize;
    private int threadCount;
    private File localFile;
    private DownloadListener mListener;
    private List<File> mCachedFiles;

    MultiThreadDownloader(String url, String savePath, String fileName, int threadNum, DownloadListener listener) {
        this.urlStr = url;
        this.dirStr = savePath;
        this.filename = fileName;
        this.threadCount = threadNum;
        this.mListener = listener;
        init();
    }

    private void init() {
        try {
            createFileByUrl();
            createCacheFile();
            downLoadFile();
        } catch (Exception ex) {
            onError(ex);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createFileByUrl() throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        NetUtil.setConnectionProperty(conn, urlStr, 0, 0);
        conn.connect();

        if (conn.getResponseCode() == NetUtil.REQUEST_CODE_SUCCESS) {
            this.fileSize = conn.getContentLength();// 根据响应获取文件大小
            if (fileSize <= 0) {
                throw new RuntimeException("the file that you download has a wrong size ... ");
            }
            this.localFile = IOUtils.newFile(dirStr, filename);
            RandomAccessFile raf = new RandomAccessFile(this.localFile, "rw");
            raf.setLength(fileSize);
            raf.close();

            mListener.onFileSize(fileSize);
        }
    }

    private void createCacheFile() throws IOException {
        mCachedFiles = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            String tempName = TEMP_FILE_PRE_FIX + i + TEMP_FILE_SUF_FIX;
            mCachedFiles.add(IOUtils.newFile(dirStr, tempName));
        }
    }

    private void downLoadFile() throws IOException {
        long blockSize = (fileSize % threadCount == 0) ? (fileSize / threadCount) : (fileSize / threadCount + 1);
        for (int i = 0; i < threadCount; i++) {
            long start = i * blockSize + mCachedFiles.get(i).length();
            long end = (i == (threadCount - 1)) ? fileSize : ((i + 1) * blockSize - 1);

            if (start < end) {
                new DownloadThread(urlStr, mCachedFiles.get(i), start, end, new OnProgressListener() {
                    @Override
                    public void onProgress(int length) {
                        downloadedLength(length);
                    }

                    @Override
                    public void onFinish() {
                        downloadFinish();
                    }

                    @Override
                    public void onError(Exception ex) {
                        MultiThreadDownloader.this.onError(ex);
                    }
                }).start();
            }
        }
    }

    private long mCurDownloadLength;
    private int mFinishedCount;

    private void downloadedLength(int length) {
        mCurDownloadLength += length;
        DownloaderTest.log("当前大小:" + mCurDownloadLength);
        if (fileSize > 0) {
            mListener.onProgress((int) (mCurDownloadLength * 100 / fileSize));
        }
    }

    private void downloadFinish() {
        mFinishedCount++;
        DownloaderTest.log("当前总完成数:" + mFinishedCount);
        if (mFinishedCount >= threadCount) {
            integrateFile();
        }
    }

    private void integrateFile() {
        mListener.onIntegrate();
        new Thread(() -> {
            long length = 0;
            for (File file : mCachedFiles) {
                length += file.length();
            }
            if (localFile.length() == length) {
                FileOutputStream os = null;
                List<FileInputStream> iss = new ArrayList<>();
                int len;
                byte[] bytes = new byte[4096];
                try {
                    os = new FileOutputStream(localFile);
                    for (File file : mCachedFiles) {
                        FileInputStream is = new FileInputStream(file);
                        iss.add(is);
                        while ((len = is.read(bytes)) != -1) {
                            os.write(bytes, 0, len);
                        }
                    }
                    mListener.onComplete();
                } catch (Exception ex) {
                    onError(ex);
                } finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                        for (FileInputStream is : iss) {
                            if (is != null) {
                                is.close();
                            }
                        }
                    } catch (Exception ex) {
                        onError(ex);
                    }
                    deleteCache();
                }
            } else {
                onError(new RuntimeException("文件大小校验不正确,取消操作!正常文件大小为:" + localFile.length() + "-- 实际碎片整合之后大小为: " + length));
            }
        }).start();
    }

    private void onError(Exception ex) {
        mListener.onError(ex);
    }

    private void deleteCache() {
        for (File temp : mCachedFiles) {
            IOUtils.delFile(temp);
        }
    }

    private static class DownloadThread extends Thread {
        private String urlStr;
        private File localFile;
        private long startPos;
        private long endPos;
        private OnProgressListener mListener;

        DownloadThread(String url, File saveFile, long startPos, long endPos, OnProgressListener listener) {
            this.urlStr = url;
            this.localFile = saveFile;
            this.startPos = startPos;
            this.endPos = endPos;
            this.mListener = listener;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                NetUtil.setConnectionProperty(conn, urlStr, startPos, endPos);
                conn.connect();
                if (conn.getResponseCode() == NetUtil.REQUEST_CODE_SUCCESS_PART) {
                    InputStream is = conn.getInputStream();
                    int len;
                    byte[] buf = new byte[4096];

                    RandomAccessFile raf = new RandomAccessFile(localFile, "rwd");
                    raf.seek(localFile.length());
                    while ((len = is.read(buf)) != -1) {
                        raf.write(buf, 0, len);
                        mListener.onProgress(len);
                    }
                    raf.close();
                    is.close();
                    mListener.onFinish();
                } else {
                    mListener.onError(new RuntimeException(threadName() + "---下载出现问题!"));
                }
            } catch (IOException e) {
                mListener.onError(e);
                e.printStackTrace();
            }
        }

        private String threadName() {
            return Thread.currentThread().getName();
        }
    }

    public interface DownloadListener {

        void onFileSize(long size);

        void onProgress(int length);

        void onIntegrate();

        void onComplete();

        void onError(Exception ex);
    }

    private interface OnProgressListener {

        void onProgress(int length);

        void onFinish();

        void onError(Exception ex);
    }
}
