package test.music;

public interface OnParseListener {
    void onStart();

    void onFinish();

    void onError(String content);
}
