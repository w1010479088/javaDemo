package test.manager;

import test.thread.RandomAccessFileDownloaderPro;
import test.thread.RandomAccessFileListener;
import test.utils.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

public class ThreadManager {

    private RandomAccessFileDownloaderPro mDownloadManager;

    public void start() {
        downloadService();
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                downloadService();
            }
        }, 3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                integrateImage();
            }
        }, 6000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mDownloadManager.deleteTempFile();
            }
        }, 9000);
    }

    private static String savePath = "tempFile/";
    private static String url = "http://a.lrlz.com/data/upload/mobile/special/s186/s186_05424853330643340.png";
    private static String fileName = "test.png";
    private static String pathName = savePath + "/" + fileName;
    private static int threadNum = 3;

    private void downloadService() {
        mDownloadManager = new RandomAccessFileDownloaderPro(url, savePath, fileName, threadNum, new RandomAccessFileListener() {

            @Override
            public void onStart(String message) {
                LogUtil.log(message);
            }

            @Override
            public void onDownloading(String message) {
                LogUtil.log(message);
            }

            @Override
            public void onPaused(String message) {
                LogUtil.log(message);
            }

            @Override
            public void onFinish(String message) {
                LogUtil.log(message);
            }

            @Override
            public void onError(Exception ex) {
                LogUtil.log(ex.getMessage());
            }
        });
    }

    private void integrateImage() {
        mDownloadManager.integrateFile();
    }
}
