package test.lock;

import test.util.LogUtil;

public class ObjectLock {
    private final Object lock = new Object();

    public static void main(String[] args) {
        ObjectLock test = new ObjectLock();
        test.fun1();
    }

    private void fun1() {
        try {
            log("进入wait...");
            lock.wait();
            log("wait结束!");
        } catch (Exception e) {
            e.printStackTrace();
            log(e.getMessage());
        }
    }

    private void log(String content) {
        LogUtil.log(content);
    }
}
