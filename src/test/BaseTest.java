package test;

import test.utils.LogUtil;

class BaseTest {

    public static void test() {
        Base sub = new Sub();
//        sub.test();
    }

    public static class Base {
        public Base() {
            this.test();
        }

        public void test() {
            LogUtil.log("---->Base" + this.hashCode());
        }
    }

    public static class Sub extends Base {
        private int subNum;

        public Sub() {
            subNum = 10;
            this.test();
        }

        public void test() {
            LogUtil.log("---->Sub" + subNum + "--->" + this.hashCode());
        }
    }
}
