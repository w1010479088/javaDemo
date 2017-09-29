package test.thread;

/**
 * Created by mac on 2017/9/29.
 */
public interface RandomAccessFileListener {

    void onStart(String message);

    void onDownloading(String message);

    void onPaused(String message);

    void onFinish(String message);

    void onError(Exception ex);
}
