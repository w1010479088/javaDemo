package test.debug;

import test.util.LogUtil;

public class DebugTest {
    private int index;
    public static void main(String[] args) {
        new DebugTest().a();
    }

    private void a() {
        LogUtil.log("a start");
        b();
        index++;
        LogUtil.log("a end");
    }

    private void b() {
        LogUtil.log("b into");
    }
}
