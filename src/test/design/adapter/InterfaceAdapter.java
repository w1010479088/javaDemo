package test.design.adapter;

import test.utils.LogUtil;

public class InterfaceAdapter {

    public static void test() {
        Source source = new Sourceable1();
        source.method1();
        source.method2();

        source = new Sourceable2();
        source.method1();
        source.method2();
    }

    public interface Source {

        void method1();

        void method2();
    }

    public static class Wrapper implements Source {

        @Override
        public void method1() {

        }

        @Override
        public void method2() {

        }
    }

    public static class Sourceable1 extends Wrapper {
        @Override
        public void method1() {
            LogUtil.log("Sourceable1 -> method1");
        }
    }

    public static class Sourceable2 extends Wrapper {
        @Override
        public void method2() {
            LogUtil.log("Sourceable2 -> method2");
        }
    }
}
