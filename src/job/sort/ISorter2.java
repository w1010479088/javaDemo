package job.sort;

import test.util.LogUtil;
import test.util.SwapUtil;

class Tester {

    public static void main(String[] args) {
        int[] contents = {5, 3, 7, 100, 2, 4, 1, 6, 0};
        ISorter2 sorter2 = new QuickSorter2();
        sorter2.sort(contents);
        LogUtil.log(contents);
    }
}

interface ISorter2 {

    void sort(int[] contents);
}

class BubbleSorter2 implements ISorter2 {

    @Override
    public void sort(int[] contents) {
        for (int i = 0; i < contents.length - 1; i++) {
            for (int j = 0; j < contents.length - 1 - i; j++) {
                if (contents[j] > contents[j + 1]) {
                    SwapUtil.swap(contents, j, j + 1);
                }
            }
        }
    }
}

class InsertSorter2 implements ISorter2 {

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

class QuickSorter2 implements ISorter2 {

    @Override
    public void sort(int[] contents) {
        sort(contents, 0, contents.length - 1);
    }

    private void sort(int[] contents, int low, int high) {
        int start = low;
        int end = high;
        int key = contents[low];

        while (start < end) {
            //右边比较
            while (start < end) {
                if (contents[end] < key) {
                    SwapUtil.swap(contents, start, end);
                    break;
                } else {
                    end--;
                }
            }

            //左边比较
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


