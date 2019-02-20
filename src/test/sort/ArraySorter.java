package test.sort;

import java.util.Arrays;

import test.util.LogUtil;

public class ArraySorter {
    public static void main(String[] args) {
        new ArraySorter(new int[]{1, 3, 5});
    }

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
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private void log() {
        LogUtil.log(Arrays.toString(array));
    }
}
