package test;

import test.util.LogUtil;

public class ReturnTest {

    public static class Base {

        protected void test() {
            if (true) {
                log("Base 的 test");
                return;
            }
            log("Base 的其他");
        }
    }

    public static class Sub extends Base {
        @Override
        protected void test() {
            super.test();
            log("Sub 的 test");
        }
    }

    public static void main(String[] args) {
        Base sub = new Sub();
        sub.test();
    }

    private static void log(String content) {
        LogUtil.log(content);
    }
}
