package test.sort;

import test.util.SwapUtil;

public class BubbleSorter implements ISortable {

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
