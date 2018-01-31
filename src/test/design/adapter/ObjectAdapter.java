package test.design.adapter;

import test.utils.LogUtil;

public class ObjectAdapter {

    public static void test() {
        Source source = new Source();
        AdapterForSource adapter = new AdapterForSource(source);
        adapter.method2();
        adapter.method3();
    }

    public static class Source {

        public void method1() {
            LogUtil.log("Source -> method1");
        }
    }

    public interface TargetAble {

        void method2();

        void method3();
    }

    public static class AdapterForSource implements TargetAble {
        private Source source;

        public AdapterForSource(Source source) {
            this.source = source;
        }

        @Override
        public void method2() {
            source.method1();
        }

        @Override
        public void method3() {
            LogUtil.log("AdapterForSource -> method3");
        }
    }
}
