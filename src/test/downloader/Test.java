package test.downloader;

import test.util.LogUtil;

public class Test {
    private static final String savePath = "tempFile/";
    private static final String url = "https://passport.lrlz.com/data/upload/package/xmmz_release.apk";
    private static final String fileName = "xmmz_release.apk";
    private static final int threadNum = 20;

    public static void main(String[] args) {
        new Test().run();
    }

    private MultiThreadDownloader mDownloader;
    private long mCurTime;

    public void run() {
        mCurTime = curTime();
        mDownloader = new MultiThreadDownloader(url, savePath, fileName, threadNum, new OnDownloadListener() {

            @Override
            public void onFileSize(long size) {
                log("文件大小:" + size);
            }

            @Override
            public void onProgress(int length) {
                log("当前进度:" + length);
            }

            @Override
            public void onIntegrate() {
                log("开始整合!");
            }

            @Override
            public void onComplete() {
                log("完毕!");
                long spendTime = (curTime() - mCurTime) / 1000;
                log("用时:" + spendTime);
            }

            @Override
            public void onError(Exception ex) {
                log(ex.getMessage());
            }
        });
    }

    private void kill() {
        log("Kill");
        if (mDownloader != null) {
            mDownloader.kill();
        }
    }

    private void log(String content) {
        LogUtil.log(content);
    }

    private long curTime() {
        return System.currentTimeMillis();
    }
}