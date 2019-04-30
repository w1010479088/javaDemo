package test.sort;

import java.util.Arrays;

import test.util.LogUtil;
import test.util.SwapUtil;

public class ArraySorter {

    private int[] array;
    private int endPos;

    private ArraySorter(int[] array) {
        this.array = array;
        this.endPos = array.length - 1;
        sort(0);
    }

    private void sort(int begin) {
        if (begin == endPos) {
            log();
        } else {
            for (int changePos = begin; changePos <= endPos; changePos++) {
                swap(begin, changePos);
                sort(begin + 1);
                swap(begin, changePos);
            }
        }
    }

    private void swap(int index1, int index2) {
        SwapUtil.swap(array, index1, index2);
    }

    private void log() {
        LogUtil.log(Arrays.toString(array));
    }
}
