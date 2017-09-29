package test.thread;

import test.utils.IOUtils;
import test.utils.NetUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2017/9/28.
 */

public class RandomAccessFileDownloaderPro {
    private static final String TEMP_FILE_PRE_FIX = "tempFile";
    private static final String TEMP_FILE_SUF_FIX = ".temp";

    private String urlStr;
    private String dirStr;
    private String filename;
    private long fileSize;
    private int threadCount;
    private File localFile;
    private RandomAccessFileListener mListener;
    private List<File> mTempFiles;

    public RandomAccessFileDownloaderPro(String url, String savePath, String fileName, int threadNum, RandomAccessFileListener listener) {
        this.urlStr = url;
        this.dirStr = savePath;
        this.filename = fileName;
        this.threadCount = threadNum;
        this.mListener = listener;
        init();
    }

    private void init() {
        new Thread(() -> {
            try {
                createFileByUrl();
                createTempFile();
                downLoadFile();
            } catch (Exception ex) {
                mListener.onError(ex);
            }
        }).start();

    }

    public void integrateFile() {
        new Thread(() -> {
            mListener.onStart("整合文件开始...");
            long length = 0;
            for (File file : mTempFiles) {
                length += file.length();
            }
            if (localFile.length() == length) {
                FileOutputStream os = null;
                List<FileInputStream> iss = new ArrayList<>();
                int len;
                byte[] bytes = new byte[4096];
                try {
                    os = new FileOutputStream(localFile);
                    for (File file : mTempFiles) {
                        FileInputStream is = new FileInputStream(file);
                        iss.add(is);
                        while ((len = is.read(bytes)) != -1) {
                            os.write(bytes, 0, len);
                        }
                    }
                    mListener.onFinish("文件整合完毕,加载文件中...");
                } catch (Exception ex) {
                    mListener.onError(ex);
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
                        mListener.onError(ex);
                    }

                }
            } else {
                mListener.onError(new RuntimeException("文件大小校验不正确,取消操作!正常文件大小为:" + localFile.length() + "-- 实际碎片整合之后大小为: " + length));
            }
        }).start();
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

            mListener.onStart("需要下载的文件大小为 :" + this.fileSize + " , 存储位置为： " + dirStr + "/" + filename);
        }
    }

    private void downLoadFile() throws IOException {
        long blockSize = (fileSize % threadCount == 0) ? (fileSize / threadCount) : (fileSize / threadCount + 1);

        for (int i = 0; i < threadCount; i++) {
            long start = i * blockSize + mTempFiles.get(i).length();
            long end = (i == (threadCount - 1)) ? fileSize : ((i + 1) * blockSize - 1);

            if (start < end) {
                new DownloadThreadPro(urlStr, mTempFiles.get(i), start, end, mListener).start();
            }
        }
    }

    private void createTempFile() throws IOException {
        mTempFiles = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            String tempName = TEMP_FILE_PRE_FIX + i + TEMP_FILE_SUF_FIX;
            mTempFiles.add(IOUtils.newFile(dirStr, tempName));
        }
    }

    public void deleteTempFile() {
        for (File temp : mTempFiles) {
            IOUtils.delFile(temp);
        }
    }
}
