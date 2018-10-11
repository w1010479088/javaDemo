package test.multi_thread_downloader;

import test.utils.LogUtil;

public class DownloaderTest {
    private static String savePath = "tempFile/";
    private static String url = "https://passport.lrlz.com/data/upload/package/xmmz_release.apk";
    private static String fileName = "xmmz_release.apk";
    private static int threadNum = 3;

    public void down() {
        downloadService();
    }

    private MultiThreadDownloader mDownloadManager;

    private void downloadService() {
        mDownloadManager = new MultiThreadDownloader(url, savePath, fileName, threadNum, new MultiThreadDownloader.DownloadListener() {

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
            }

            @Override
            public void onError(Exception ex) {
                log(ex.getMessage());
            }
        });
    }

    public static void log(String content) {
        LogUtil.log(content);
    }
}
