package test.chartest;

import java.nio.charset.Charset;

import test.util.LogUtil;

public class ChineseChar {
    private static final String TEST_CHAR = "我是ABC的测！";

    public static void main(String[] args) {
        String result = new ChineseChar().slice(TEST_CHAR, 3);
        log("结果：" + result);
    }

    private String slice(String content, int bytePos) {
        StringBuilder sb = new StringBuilder();
        int curBytePos = 0;
        char[] chars = content.toCharArray();
        for (char item : chars) {
            int byteLen = charLen(item);
            curBytePos += byteLen;
            if (curBytePos > bytePos) {
                break;
            } else {
                sb.append(String.valueOf(item));
            }
        }
        return sb.toString();
    }

    private Charset getCharSet() {
        return Charset.forName("gbk");
    }

    private int charLen(char charContent) {
        return String.valueOf(charContent).getBytes(getCharSet()).length;
    }

    private static void log(String content) {
        LogUtil.log(content);
    }
}
