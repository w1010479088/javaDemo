package test.interview;

import java.nio.charset.Charset;

import test.util.LogUtil;

public class ChineseChar {
    //中文字符问题，在不同平台byte占位不同

    private static final String TEST_CHAR = "我是ABC的测！";

    public static void main(String[] args) {
        String result = new ChineseChar().slice(TEST_CHAR, 3);
        log("结果：" + result);
    }

    public String slice(String content, int bytePos) {
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


class BottleTest {
    //喝汽水的问题

    public static void main(String[] args) {
        new BottleTest().swap(4);
    }

    private int count = 0;
    private int left_bottle = 0;
    private static final int RATIO = 2;

    public void swap(int money) {
        count += money;
        left_bottle += money;

        if (left_bottle >= RATIO) {
            int tempMoney = left_bottle / RATIO;
            left_bottle = left_bottle % RATIO;
            swap(tempMoney);
        } else {
            LogUtil.log(String.format("兑换%2d,剩余瓶子%2d", count, left_bottle));
        }
    }
}

//冒泡排序
class BubbleSort {
    public static void main(String[] args) {
        int[] sorted = new BubbleSort().sort(TEST);

        StringBuilder sb = new StringBuilder();
        for (int item : sorted) {
            sb.append(String.valueOf(item));
            sb.append("\n");
        }
        LogUtil.log(sb.toString());
    }

    private static final int[] TEST = new int[]{8, 3, 1, 4, 5, 2};

    public int[] sort(int[] content) {
        for (int i = 0; i < content.length - 1; i++) {
            for (int j = 0; j < content.length - 1 - i; j++) {
                if (content[j] > content[j + 1]) {
                    swap(j, j + 1, content);
                }
            }
        }
        return content;
    }

    private void swap(int fromPos, int toPos, int[] data) {
        int temp = data[toPos];
        data[toPos] = data[fromPos];
        data[fromPos] = temp;
    }
}