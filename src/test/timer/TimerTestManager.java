package test.timer;

import test.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTestManager {

    public static void test() {
        LogUtil.log("定时器开始!");
        TimerCounter.start();
        List<TimerListener> listeners = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listeners.add(new TimerTestModel(i));
        }

        for (TimerListener listener : listeners) {
            TimerCounter.register(listener);
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                TimerCounter.unRegister(listeners.get(3));
                TimerCounter.unRegister(listeners.get(0));
                LogUtil.log("---移除了0,3位置的Listener---");
            }
        }, 10 * 1000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                TimerCounter.stop();
                LogUtil.log("---定时器关闭!---");
            }
        }, 20 * 1000);
    }

    private static class TimerTestModel implements TimerListener {
        private int tag;

        private TimerTestModel(int tag) {
            this.tag = tag;
        }

        @Override
        public void onUpdate() {
            LogUtil.log("----" + tag + "----");
        }
    }
}
