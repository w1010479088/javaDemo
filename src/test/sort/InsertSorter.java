package test.sort;

import test.util.SwapUtil;

public class InsertSorter implements ISortable {

    @Override
    public int[] sort(int[] content) {
        for (int i = 0; i < content.length; i++) {
            for (int j = i; j > 0; j--) {
                if (content[j] < content[j - 1]) {
                    SwapUtil.swap(content, j, j - 1);
                } else {
                    break;
                }
            }
        }
        return content;
    }
}
