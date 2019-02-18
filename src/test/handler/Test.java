package test.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import test.util.LogUtil;

public class Test {
    private static final int MSG_GET = 1;
    private static final int MSG_RESULT = 2;
    private Handler mMainHandler;
    private Handler mWorkHandler;

    public void init() {
        initThread();
        initWork();
    }

    private void initThread() {
        HandlerThread workThread = new HandlerThread("main");
        workThread.start();
        mMainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_RESULT:
                        log(String.valueOf(msg.arg1));
                        break;
                    default:
                        break;
                }
            }
        };
        mWorkHandler = new Handler(workThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_GET:
                        Message newMsg = new Message();
                        newMsg.what = MSG_RESULT;
                        newMsg.arg1 = 12;
                        mMainHandler.dispatchMessage(newMsg);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initWork() {
        mWorkHandler.sendEmptyMessage(MSG_GET);
    }

    private void log(String content) {
        LogUtil.log(content);
    }
}

class HandlerTest {
    private static final int MSG_FIND = 1;
    private static final int MSG_DISCARD = 2;

    private Handler mWorker;

    private void initHandlerThread() {
        HandlerThread workThread = new HandlerThread("work_thread");
        workThread.start();
        mWorker = new Handler(workThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                handleMsg(msg);
            }
        };
    }

    private void initNormalThread() {
        Thread thread = new Thread(() -> {
            Looper.prepare();
            mWorker = new Handler(Looper.myLooper());
            Looper.loop();
        }, "worker");
        thread.start();
    }

    public void work(Message msg) {
        mWorker.sendMessage(msg);
    }

    public void work(Runnable runnable) {
        mWorker.post(runnable);
    }

    public void work(Runnable runnable, int delay_seconds) {
        mWorker.postDelayed(runnable, delay_seconds * 1000);
    }

    private void handleMsg(Message msg) {
        switch (msg.what) {
            case MSG_FIND:
                msgFind();
                break;
            case MSG_DISCARD:
                msgDiscard();
                break;
        }
    }

    private void msgFind() {
        log("MsgFind");
    }

    private void msgDiscard() {
        log("MsgDiscard");
    }

    private void log(String content) {
        LogUtil.log(content);
    }
}

class CollectionTest {

    private void test() {

    }
}