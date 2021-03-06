package test.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerCounter {

    private TimerCounter() {
    }

    private static class InstanceHolder {
        private static TimerCounter instance = new TimerCounter();
    }

    private static TimerCounter getInstance() {
        return InstanceHolder.instance;
    }

    public static void start() {
        getInstance().startTimer();
    }

    public static void stop() {
        getInstance().stopTimer();
    }

    public static void register(TimerListener listener) {
        getInstance().addListener(listener);
    }

    public static void unRegister(TimerListener listener) {
        getInstance().removeListener(listener);
    }


    private List<TimerListener> mListeners = new ArrayList<>();
    private Timer mTimer;
    private boolean mStop;

    private void addListener(TimerListener listener) {
        mListeners.add(listener);
    }

    private void removeListener(TimerListener listener) {
        mListeners.remove(listener);
    }

    private void startTimer() {
        if (mTimer == null) {
            mStop = false;
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    update();
                }
            }, 0, 1000);
        }
    }

    private void stopTimer() {
        mStop = true;
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            mListeners.clear();
        }
    }

    private void update() {
        if (mStop) return;
        for (TimerListener listener : mListeners) {
            if (listener != null) {
                if (mStop) break;
                listener.onUpdate();
            }
        }
    }
}
