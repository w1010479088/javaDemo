package interview;

import test.util.LogUtil;

public class Tester {
    public static void main(String[] args) {
        Integer[] nums = {3, 2, 1, 4, 6, 5};
        new Test2().sort(nums);
        for (Integer item : nums) {
            LogUtil.log(item.toString());
        }
    }
}
