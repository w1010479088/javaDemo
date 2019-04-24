package test.sort;

import test.util.LogUtil;

public class BinarySearch {
    private static final int[] TEST = {0, 3, 5, 9, 20, 99};
    private static final int target = 21;

    public static void main(String[] args) {
//        int pos = new BinarySearch().find(0, TEST.length - 1);
        int pos = new BinarySearch().find(1);
        LogUtil.log(String.format("Pos=%d", pos));
    }

    public int find(int fromPos, int toPos) {
        if (fromPos > toPos) {
            return -1;
        }
        int pos = (fromPos + toPos) / 2;
        if (TEST[pos] > target) {
            return find(fromPos, pos - 1);
        } else if (TEST[pos] < target) {
            return find(pos + 1, toPos);
        } else {
            return pos;
        }
    }

    public int find(int key) {
        int fromPos = 0;
        int toPos = TEST.length - 1;
        while (fromPos <= toPos) {
            int curPos = (fromPos + toPos) / 2;
            int curData = TEST[curPos];
            if (curData > key) {
                toPos = curPos - 1;
            } else if (curData < key) {
                fromPos = curPos + 1;
            } else {
                return curPos;
            }
        }
        return -1;
    }
}
