package test.music;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import test.utils.LogUtil;

public class ParseNetEase {
    private static final long CODE = 0xa3;

    public static void main(String[] args) {
        new ParseNetEase().parse("tempFile/1_test.mp3", "tempFile/一百万个可能.mp3", CODE, new OnParseListener() {
            @Override
            public void onStart() {
                log("任务开始!");
            }

            @Override
            public void onFinish() {
                log("正常解析完毕!");
            }

            @Override
            public void onError(String content) {
                log(content);
            }
        });
    }

    private void parse(String inputPath, String outputPath, long code, OnParseListener listener) {
        listener.onStart();
        File input = new File(inputPath);
        if (input.exists() && input.isFile()) {
            File output = new File(outputPath);
            FileInputStream inputStream = null;
            FileOutputStream outputStream = null;
            try {
                inputStream = new FileInputStream(input);
                outputStream = new FileOutputStream(output);
                byte[] bytes = new byte[1024];
                int len;
                while ((len = (inputStream.read(bytes))) != -1) {
                    for (int i = 0; i < len; i++) {
                        bytes[i] ^= code;
                    }
                    outputStream.write(bytes, 0, len);
                }
                listener.onFinish();
            } catch (Exception ex) {
                listener.onError(ex.getMessage());
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Exception ex) {
                    listener.onError(ex.getMessage());
                }
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (Exception ex) {
                    listener.onError(ex.getMessage());
                }
            }
        }
    }


    private static void log(String content) {
        LogUtil.log(content);
    }

    public interface OnParseListener {
        void onStart();

        void onFinish();

        void onError(String content);
    }
}
