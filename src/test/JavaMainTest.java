package test;

import test.strategy.Calcluater;
import test.utils.LogUtil;

public class JavaMainTest {
    private static int count;
    public static void main(String[] args) {
        testMethod();
    }

    private static void testMethod() {
        LogUtil.log("------------> Java开始运行!");
        testHandler();
        LogUtil.log("------------> Java运行结束!");
    }

    private void testCalculater() {
        count = 6;
        float calculate = new Calcluater(213F).calculate();
        LogUtil.log("------------> calculate:" + calculate);
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("大俊子");
        local.get();
        local.remove();
    }

    private static void testHandler() {
        new test.handler.Test().init();
    }

    public synchronized int getA() {
        return 1;
    }

    public synchronized int getB() {
        return 2;
    }
}
