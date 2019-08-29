package test.sort;

import test.util.LogUtil;
import test.util.SwapUtil;

public class QuickSorter implements ISortable {

    @Override
    public void sort(int[] content) {
        sort(content, 0, content.length - 1);
    }

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

        if (start < high) {
            sort(content, start + 1, high);
        }
    }
}
