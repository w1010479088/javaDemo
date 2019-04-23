package test.sort;

import test.util.LogUtil;
import test.util.SwapUtil;

public class BubbleSorter implements ISortable {
    private static final int[] TEST = new int[]{8, 3, 1, 4, 5, 2};

    public static void main(String[] args) {
        int[] sorted = new BubbleSorter().sort(TEST);

        StringBuilder sb = new StringBuilder();
        for (int item : sorted) {
            sb.append(String.valueOf(item));
            sb.append("\n");
        }
        LogUtil.log(sb.toString());
    }

    @Override
    public int[] sort(int[] content) {
        for (int i = 0; i < content.length - 1; i++) {
            for (int j = 0; j < content.length - 1 - i; j++) {
                if (content[j] > content[j + 1]) {
                    SwapUtil.swap(content, j, j + 1);
                }
            }
        }
        return content;
    }
}
