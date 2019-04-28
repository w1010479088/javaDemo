package test.downloader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import test.util.IOUtils;
import test.util.LogUtil;

/**
 * Created by mac on 2017/9/28.
 */

class MultiThreadDownloader implements IKillble {
    private static final String TEMP_FILE_PRE_FIX = "tempFile";
    private static final String TEMP_FILE_SUF_FIX = ".temp";

    private long mFileSize;
    private int mThreadCount;
    private String mDirStr;
    private String mFilename;
    private File mSourceFile;
    private OnDownloadListener mListener;
    private ExecutorService mExecutor;
    private AtomicLong mCurLength = new AtomicLong();
    private List<File> mCacheFiles = new ArrayList<>();
    private List<IKillble> mKillables = new ArrayList<>();

    MultiThreadDownloader(String url, String savePath, String fileName, int threadNum, OnDownloadListener listener) {
        this.mDirStr = savePath;
        this.mFilename = fileName;
        this.mThreadCount = threadNum;
        this.mListener = listener;
        this.mExecutor = Executors.newFixedThreadPool(threadNum);
        init(url);
    }

    @Override
    public void kill() {
        stopDownload();
        closeExecutors();
    }

    private void stopDownload() {
        for (IKillble item : mKillables) {
            item.kill();
        }
    }

    private void init(String urlStr) {
        mExecutor.submit(() -> {
            try {
                createSourceFile(urlStr);
                createCacheFile();
                downLoadFile(urlStr);
            } catch (Exception ex) {
                onError(ex);
                closeExecutors();
            }
        });
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createSourceFile(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        NetUtil.setConnectionProperty(conn, urlStr, 0, 0);
        conn.connect();

        if (conn.getResponseCode() == NetUtil.REQUEST_CODE_SUCCESS) {
            this.mFileSize = conn.getContentLength();// 根据响应获取文件大小
            if (getFileSize() <= 0) {
                throw new RuntimeException("the file that you download has a wrong size ... ");
            }
            this.mSourceFile = IOUtils.newFile(mDirStr, mFilename);
            RandomAccessFile raf = new RandomAccessFile(mSourceFile, "rw");
            raf.setLength(getFileSize());
            raf.close();

            mListener.onFileSize(getFileSize());
        }
    }

    private void createCacheFile() throws IOException {
        for (int i = 0; i < mThreadCount; i++) {
            String tempName = TEMP_FILE_PRE_FIX + i + TEMP_FILE_SUF_FIX;
            mCacheFiles.add(IOUtils.newFile(mDirStr, tempName));
        }
    }

    private void downLoadFile(String urlStr) throws IOException {
        long itemSize = (getFileSize() % mThreadCount == 0) ? (getFileSize() / mThreadCount) : (getFileSize() / mThreadCount + 1);
        for (int i = 0; i < mThreadCount; i++) {
            File itemFile = mCacheFiles.get(i);
            long downloadedLength = itemFile.length();
            addLength(downloadedLength);
            long start = i * itemSize + downloadedLength;
            long end = (i == (mThreadCount - 1)) ? getFileSize() : ((i + 1) * itemSize - 1);

            if (start < end) {
                DownloadTask downloadTask = new DownloadTask(urlStr, itemFile, start, end, new OnItemThreadListener() {
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
                mKillables.add(downloadTask);
                mExecutor.submit(downloadTask);
            }
        }
        checkCompleted();
    }

    private void checkCompleted() {
        updateProgress();
        if (getLength() == getFileSize()) {
            integrateFile();
        }
    }

    private void downloadedLength(String threadName, int length) {
        addLength(length);
        log("当前大小:" + threadName + "|" + getLength());
        checkCompleted();
    }

    private void updateProgress() {
        if (getFileSize() > 0) {
            mListener.onProgress((int) (getLength() * 100 / getFileSize()));
        }
    }

    private void addLength(long length) {
        mCurLength.addAndGet(length);
    }

    private long getLength() {
        return mCurLength.get();
    }

    private long getFileSize() {
        return mFileSize;
    }

    private void integrateFile() {
        mListener.onIntegrate();
        mExecutor.execute(new FileIntegrater(mSourceFile, mCacheFiles, new IntegrateListener() {
            @Override
            public void log(String content) {
                MultiThreadDownloader.this.log(content);
            }

            @Override
            public void complete() {
                MultiThreadDownloader.this.complete();
            }

            @Override
            public void onError(Exception ex) {
                MultiThreadDownloader.this.onError(ex);
            }
        }));
    }

    private void onError(Exception ex) {
        mListener.onError(ex);
    }

    private void closeExecutors() {
        if (mExecutor != null && !mExecutor.isShutdown()) {
            mExecutor.shutdown();
        }
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
        closeExecutors();
    }
}
