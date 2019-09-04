package job.concurrent;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

class Test {
    public void a() {
        Context context = null;
        String url = null;
        Downloader downloader = new Downloader(context);
        downloader.execute(url);
    }
}

class Downloader extends AsyncTask<String, Integer, Void> {
    private ProgressDialog mProgress;

    Downloader(Context context) {
        this.mProgress = new ProgressDialog(context);
        this.mProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    @Override
    protected void onPreExecute() {
        mProgress.show();
    }

    @Override
    protected Void doInBackground(String... url) {
        //do Logic
        publishProgress(100);
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        mProgress.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.cancel();
        }
    }
}