package test.design.adapter;


import test.util.LogUtil;

public class ClazzAdapter {

    public static void test() {
        AdapterForSource adapter = new AdapterForSource();
        adapter.method2();
        adapter.method3();
    }

    public static class Source {

        public void method1() {
            LogUtil.log("Source -> method1");
        }
    }

    public interface Targetable {

        void method2();

        void method3();
    }

    public static class AdapterForSource extends Source implements Targetable {

        @Override
        public void method2() {
            method1();
        }

        @Override
        public void method3() {
            LogUtil.log("AdapterForSource -> method3");
        }
    }
}
