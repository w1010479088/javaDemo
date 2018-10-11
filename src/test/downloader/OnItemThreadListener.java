package test.downloader;

public interface OnItemThreadListener {

    void onLength(String threadName, int length);

    void onFinish();

    void onError(Exception ex);
}
