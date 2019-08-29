package test.util;

public class SwapUtil {
    public static void swap(int[] content, int a, int b) {
        if (content == null || a > content.length - 1 || b > content.length - 1 || a < 0 || b < 0) {
            throw new ArrayIndexOutOfBoundsException("数组越界，SwapUtil中swap方法！");
        }
        if (a == b) {
            return;
        }
        int temp = content[a];
        content[a] = content[b];
        content[b] = temp;
    }
}
