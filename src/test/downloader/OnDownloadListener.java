package test.downloader;

public interface OnDownloadListener {

    void onFileSize(long size);

    void onProgress(int length);

    void onIntegrate();

    void onComplete();

    void onError(Exception ex);
}
