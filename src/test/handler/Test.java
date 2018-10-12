package test.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import test.utils.LogUtil;

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
