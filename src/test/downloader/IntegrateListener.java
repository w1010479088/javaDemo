package test.downloader;

public interface IntegrateListener {

    void log(String content);

    void complete();

    void onError(Exception ex);
}
