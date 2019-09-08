package job.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;

class Test {

}

abstract class IntentService extends Service {
    private Handler mHandler;
    private HandlerThread mWorkThread;

    @Override
    public void onCreate() {
        mWorkThread = new HandlerThread("IntentService");
        mWorkThread.start();
        mHandler = new Handler(mWorkThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                handlerMsg(msg);
            }
        };

    }

    private void handlerMsg(Message msg) {
        int startId = msg.arg1;
        Intent intent = (Intent) msg.obj;
        onHandleIntent(startId, intent);
        stopSelf(startId);
    }

    public abstract void onHandleIntent(int startId, Intent intent);

    @Override
    public void onStart(Intent intent, int startId) {
        Message msg = Message.obtain();
        msg.arg1 = startId;
        msg.obj = intent;
        mHandler.sendMessage(msg);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mWorkThread.quitSafely();
        mWorkThread = null;
        mHandler = null;
    }
}