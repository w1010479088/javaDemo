package test.util;

public class SwapUtil {
    public static void swap(int[] data, int fromPos, int toPos) {
        int temp = data[toPos];
        data[toPos] = data[fromPos];
        data[fromPos] = temp;
    }
}
