package test.sort;

import test.util.LogUtil;
import test.util.SwapUtil;

public class QuickSorter implements ISortable {

    public static void main(String[] args) {
        int[] sorted = new QuickSorter().sort(TEST);
        LogUtil.log(sorted);
    }

    @Override
    public int[] sort(int[] content) {
        sort(TEST, 0, TEST.length - 1);
        return TEST;
    }

    private static final int[] TEST = {12, 20, 1, 5};

    private void sort(int[] content, int low, int high) {
        int start = low;
        int end = high;
        int key = content[low];

        //从后往前
        while (start < end) {
            while (start < end) {
                if (content[end] < key) {
                    SwapUtil.swap(content, start, end);
                    break;
                } else {
                    end--;
                }
            }

            LogUtil.log(content);

            //从前往后
            while (start < end) {
                if (content[start] > key) {
                    SwapUtil.swap(content, start, end);
                    break;
                } else {
                    start++;
                }
            }

            LogUtil.log(content);
        }

        if (start > low) {
            sort(content, low, start - 1);
        }

        if (end < high) {
            sort(content, end + 1, high);
        }
    }
}
