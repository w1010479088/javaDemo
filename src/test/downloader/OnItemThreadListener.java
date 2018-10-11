package test.downloader;

public interface OnItemThreadListener {

    void onProgress(int length);

    void onFinish();

    void onError(Exception ex);
}
