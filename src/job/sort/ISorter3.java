package job.sort;

import test.util.LogUtil;
import test.util.SwapUtil;

class Test3 {
    public static void main(String[] args) {
//        int[] contents = {5, 3, 1};
//        ISorter3 sorter = new FullSorter3();
//        sorter.sort(contents);
//
//        LogUtil.log(contents);
        String content = new StringReserver().reserve("abc");
        LogUtil.log(content);
    }
}

interface ISorter3 {
    void sort(int[] contents);
}

class BubbleSorter3 implements ISorter3 {

    @Override
    public void sort(int[] contents) {
        for (int i = 0; i < contents.length - 1; i++) {
            for (int j = 0; j < contents.length - i - 1; j++) {
                if (contents[j] > contents[j + 1]) {
                    SwapUtil.swap(contents, j, j + 1);
                }
            }
        }
    }
}

class InsertSorter3 implements ISorter3 {

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

class QuickSorter3 implements ISorter3 {

    @Override
    public void sort(int[] contents) {
        sort(contents, 0, contents.length - 1);
    }

    private void sort(int[] contents, int low, int high) {
        int start = low;
        int end = high;
        int key = contents[low];

        while (start < end) {
            while (start < end) {
                if (contents[end] < key) {
                    SwapUtil.swap(contents, start, end);
                    break;
                } else {
                    end--;
                }
            }

            while (start < end) {
                if (contents[start] > key) {
                    SwapUtil.swap(contents, start, end);
                    break;
                } else {
                    start++;
                }
            }
        }

        if (start > low) {
            sort(contents, low, start - 1);
        }

        if (start < high) {
            sort(contents, start, end - 1);
        }
    }
}

class FullSorter3 implements ISorter3 {

    @Override
    public void sort(int[] contents) {
        fullSort(contents, 0, contents.length - 1);
    }

    private void fullSort(int[] contents, int start, int end) {
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

class StringReserver {

    public String reserve(String content) {
        if (content == null || content.length() <= 1) {
            return content;
        } else {
            String temp = content.substring(1);
            return reserve(temp) + content.charAt(0);
        }
    }
}