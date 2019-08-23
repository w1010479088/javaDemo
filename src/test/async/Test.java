package test.async;

import android.os.AsyncTask;

import test.util.LogUtil;

public class Test {
    public static void main(String[] args) {
        new AsyncTaskA().execute("大俊子");
    }
}

class AsyncTaskA extends AsyncTask<String, Integer, Integer> {

    @Override
    protected void onPreExecute() {
        LogUtil.log("onPreExecute");
    }

    @Override
    protected Integer doInBackground(String... strings) {
        LogUtil.log("doInBackground");
        return 20;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        LogUtil.log("onProgressUpdate");
    }

    @Override
    protected void onPostExecute(Integer integer) {
        LogUtil.log("onPostExecute");
    }
}
