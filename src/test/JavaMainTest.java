package test;

import test.strategy.Calcluater;
import test.utils.LogUtil;

public class JavaMainTest {

    public static void main(String[] args) {
        testMethod();
    }

    private static void testMethod() {
        LogUtil.log("------------> Java开始运行!");
        float calculate = new Calcluater(213F).calculate();
        LogUtil.log("------------> calculate:" + calculate);
        LogUtil.log("------------> Java运行结束!");
    }
}
