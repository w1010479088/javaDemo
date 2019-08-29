package job.sort;

import test.util.LogUtil;
import test.util.SwapUtil;

class Test {
    public static void main(String[] args) {
//        int[] contents = {5, 3, 7, 100, 2, 4, 1, 6, 0};
//        ISorter sorter = new InsertSorter();
//        sorter.sort(contents);
//        LogUtil.log(contents);

        int[] contents = {5, 3, 7, 4};
        fullSort(contents, 0, contents.length - 1);
    }

    private static void fullSort(int[] contents, int start, int end) {
        if (start == end) {
            LogUtil.log(contents);
            return;
        }

        for (int i = start; i <= end; i++) {
            SwapUtil.swap(contents, start, i);
            fullSort(contents, start + 1, end);
            SwapUtil.swap(contents, start, i);
        }
    }
}

interface ISorter {

    void sort(int[] contents);
}

class BubbleSorter implements ISorter {

    @Override
    public void sort(int[] contents) {
        for (int i = 0; i < contents.length - 1; i++) {
            for (int j = 0; j < contents.length - 1 - i; j++) {
                if (contents[j] > contents[j + 1]) {
                    test.util.SwapUtil.swap(contents, j, j + 1);
                }
            }
        }
    }
}

class QuickerSorter implements ISorter {

    @Override
    public void sort(int[] contents) {
        sort(contents, 0, contents.length - 1);
    }

    private void sort(int[] contents, int low, int high) {
        int start = low;
        int end = high;
        int key = contents[low];

        while (start < end) {
            //从右开始
            while (start < end) {
                if (contents[end] < key) {
                    SwapUtil.swap(contents, start, end);
                    break;
                } else {
                    end--;
                }
            }

            //从左开始
            while (start < end) {
                if (contents[start] > key) {
                    SwapUtil.swap(contents, start, end);
                    break;
                } else {
                    start++;
                }
            }
        }

        //左边递归
        if (start > low) {
            sort(contents, low, start - 1);
        }

        //右边递归
        if (start < high) {
            sort(contents, start + 1, high);
        }
    }
}

class InsertSorter implements ISorter {

    @Override
    public void sort(int[] contents) {
        for (int i = 1; i < contents.length; i++) {
            for (int j = i; j > 0; j--) {
                if (contents[j] < contents[j - 1]) {
                    SwapUtil.swap(contents, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
