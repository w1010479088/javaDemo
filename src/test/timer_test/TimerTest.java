package test.timer_test;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimerTest {
    public void test() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

            }
        }, 1 * 1000, 2 * 1000, TimeUnit.MILLISECONDS);
    }
}
