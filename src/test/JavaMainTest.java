package test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import test.strategy.Calcluater;
import test.utils.LogUtil;

public class JavaMainTest {
    private static int count;

    public static void main(String[] args) {
        testMethod();
    }

    private static void testMethod() {
        LogUtil.log("------------> Java开始运行!");
        testHandler();
        LogUtil.log("------------> Java运行结束!");
    }

    private void testCalculater() {
        count = 6;
        float calculate = new Calcluater(213F).calculate();
        LogUtil.log("------------> calculate:" + calculate);
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("大俊子");
        local.get();
        local.remove();
    }

    private static void testHandler() {
        new test.handler.Test().init();
    }

    public synchronized int getA() {
        return 1;
    }

    public synchronized int getB() {
        return 2;
    }

    private void testFuture() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        });
        boolean futureDone = future.isDone();
        try {
            Integer result = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
