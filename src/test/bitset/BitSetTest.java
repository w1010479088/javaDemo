package test.bitset;


import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

import test.util.LogUtil;

public class BitSetTest {

    private static final int COUNT = 20;

    public static void test() {
        create();
    }

    private static void create() {
        List<Integer> nums = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < COUNT; i++) {
            nums.add(random.nextInt(10) + 1);
        }
        LogUtil.log(nums.toString());

        BitSet bitSet = new BitSet(COUNT);
        for (Integer integer : nums) {
            bitSet.set(integer);
        }

        List<Integer> sortData = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            if (bitSet.get(i)) {
                sortData.add(i);
            }
        }

        LogUtil.log(sortData.toString());
    }
}
