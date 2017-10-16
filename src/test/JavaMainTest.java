package test;

import test.utils.LogUtil;

public class JavaMainTest {

    public static void main(String[] args) {
        LogUtil.log("大俊子在此!");
//        new ThreadManager().start();
        testInteger();
    }

    private static void testInteger() {
        LogUtil.log("Integer最大值:" + Integer.MAX_VALUE);
        LogUtil.log("Integer最小值:" + Integer.MIN_VALUE);
    }
}
