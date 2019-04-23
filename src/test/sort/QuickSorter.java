package test.sort;

import test.util.LogUtil;

public class QuickSorter implements ISortable {
    private static final int[] TEST = new int[]{8, 3, 1, 4, 5, 2};

    public static void main(String[] args) {
        int[] sorted = new QuickSorter().sort(TEST);
        LogUtil.log(sorted);
    }

    @Override
    public int[] sort(int[] content) {
        return new int[0];
    }
}
