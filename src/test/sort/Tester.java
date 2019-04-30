package test.sort;

import test.util.LogUtil;

public class Tester {
    private static final int[] TEST = new int[]{8, 3, 1, 4, 5, 2};

    public static void main(String[] args) {
        LogUtil.log(new InsertSorter().sort(TEST));
    }
}
