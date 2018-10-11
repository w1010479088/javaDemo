package test;

import test.downloader.Test;
import test.strategy.Calcluater;
import test.utils.LogUtil;

public class JavaMainTest {

    public static void main(String[] args) {
        testMethod();
    }

    private static void testMethod() {
        LogUtil.log("------------> Java开始运行!");
        new Test().run();
        LogUtil.log("------------> Java运行结束!");
    }

    private void testCalculater() {
        float calculate = new Calcluater(213F).calculate();
        LogUtil.log("------------> calculate:" + calculate);
    }
}
