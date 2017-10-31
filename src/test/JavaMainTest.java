package test;

import test.timer.TimerTestManager;
import test.utils.LogUtil;

public class JavaMainTest {

    public static void main(String[] args) {
        testMethod();
    }

    private static void testMethod() {
        LogUtil.log("Java开始运行!");
//        new ThreadManager().start();
//        testInteger();
        testTimerCounter();
    }

    private static void testTimerCounter() {
        TimerTestManager.test();
    }

    private static void testInteger() {
        LogUtil.log("Integer最大值:" + Integer.MAX_VALUE);
        LogUtil.log("Integer最小值:" + Integer.MIN_VALUE);
    }
}
