package test.downloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import test.utils.IOUtils;
import test.utils.LogUtil;

/**
 * Created by mac on 2017/9/28.
 */

class MultiThreadDownloader implements IKillble {
    private static final String TEMP_FILE_PRE_FIX = "tempFile";
    private static final String TEMP_FILE_SUF_FIX = ".temp";

    private long mFileSize;
    private long mCurLength;
    private int mThreadCount;
    private String mDirStr;
    private String mFilename;
    private File mSourceFile;
    private List<File> mCacheFiles = new ArrayList<>();
    private List<IKillble> mKillables = new ArrayList<>();
    private OnDownloadListener mListener;

    MultiThreadDownloader(String url, String savePath, String fileName, int threadNum, OnDownloadListener listener) {
        this.mDirStr = savePath;
        this.mFilename = fileName;
        this.mThreadCount = threadNum;
        this.mListener = listener;
        init(url);
    }

    @Override
    public void kill() {
        stopDownload();
    }

    private void stopDownload() {
        for (IKillble item : mKillables) {
            item.kill();
        }
    }

    private void init(String urlStr) {
        new Thread(() -> {
            try {
                createSourceFile(urlStr);
                createCacheFile();
                downLoadFile(urlStr);
            } catch (Exception ex) {
                onError(ex);
            }
        }).start();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createSourceFile(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        NetUtil.setConnectionProperty(conn, urlStr, 0, 0);
        conn.connect();

        if (conn.getResponseCode() == NetUtil.REQUEST_CODE_SUCCESS) {
            this.mFileSize = conn.getContentLength();// 根据响应获取文件大小
            if (mFileSize <= 0) {
                throw new RuntimeException("the file that you download has a wrong size ... ");
            }
            this.mSourceFile = IOUtils.newFile(mDirStr, mFilename);
            RandomAccessFile raf = new RandomAccessFile(mSourceFile, "rw");
            raf.setLength(mFileSize);
            raf.close();

            mListener.onFileSize(mFileSize);
        }
    }

    private void createCacheFile() throws IOException {
        for (int i = 0; i < mThreadCount; i++) {
            String tempName = TEMP_FILE_PRE_FIX + i + TEMP_FILE_SUF_FIX;
            mCacheFiles.add(IOUtils.newFile(mDirStr, tempName));
        }
    }

    private void downLoadFile(String urlStr) throws IOException {
        long itemSize = (mFileSize % mThreadCount == 0) ? (mFileSize / mThreadCount) : (mFileSize / mThreadCount + 1);
        for (int i = 0; i < mThreadCount; i++) {
            File itemFile = mCacheFiles.get(i);
            long downloadedLength = itemFile.length();
            mCurLength += downloadedLength;
            long start = i * itemSize + downloadedLength;
            long end = (i == (mThreadCount - 1)) ? mFileSize : ((i + 1) * itemSize - 1);

            if (start < end) {
                DownloadThread downloadThread = new DownloadThread(urlStr, itemFile, start, end, new OnItemThreadListener() {
                    @Override
                    public void onLength(String threadName, int length) {
                        downloadedLength(threadName, length);
                    }

                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onError(Exception ex) {
                        MultiThreadDownloader.this.onError(ex);
                    }
                });
                mKillables.add(downloadThread);
                downloadThread.start();
            }
        }
        checkCompleted();
    }

    private void checkCompleted() {
        updateProgress();
        if (mCurLength == mFileSize) {
            integrateFile();
        }
    }

    private synchronized void downloadedLength(String threadName, int length) {
        mCurLength += length;
        log("当前大小:" + threadName + "|" + mCurLength);
        checkCompleted();
    }

    private void updateProgress() {
        if (mFileSize > 0) {
            mListener.onProgress((int) (mCurLength * 100 / mFileSize));
        }
    }

    private void integrateFile() {
        mListener.onIntegrate();
        new Thread(() -> {
            long length = 0;
            for (File file : mCacheFiles) {
                length += file.length();
            }
            if (mSourceFile.length() == length) {
                FileOutputStream os = null;
                List<FileInputStream> iss = new ArrayList<>();
                int len;
                byte[] bytes = new byte[4096];
                try {
                    os = new FileOutputStream(mSourceFile);
                    for (File file : mCacheFiles) {
                        FileInputStream is = new FileInputStream(file);
                        iss.add(is);
                        while ((len = is.read(bytes)) != -1) {
                            os.write(bytes, 0, len);
                        }
                    }
                    complete();
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
                }
            } else {
                log("文件大小校验不正确,取消操作!正常文件大小为:" + mSourceFile.length() + "-- 实际碎片整合之后大小为: " + length);
            }
        }).start();
    }

    private void onError(Exception ex) {
        mListener.onError(ex);
    }

    private void deleteCache() {
        for (File cacheFile : mCacheFiles) {
            IOUtils.delFile(cacheFile);
        }
    }

    private void log(String content) {
        LogUtil.log(content);
    }

    private void complete() {
        deleteCache();
        mListener.onComplete();
        mKillables.clear();
        mCacheFiles.clear();
    }
}
