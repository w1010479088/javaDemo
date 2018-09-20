package test;

import test.utils.LogUtil;
import static test.kotlin.TestKotlinBKt.testStringObject;

public class JavaMainTest {

    public static void main(String[] args) {
        testMethod();
    }

    private static void testMethod() {
        LogUtil.log("------------> Java开始运行!");
        testStringObject();
        LogUtil.log("------------> Java运行结束!");
    }
}
