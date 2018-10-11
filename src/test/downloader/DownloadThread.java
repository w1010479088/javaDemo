package test.downloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

class DownloadThread extends Thread implements IKillble {
    private String urlStr;
    private File localFile;
    private long startPos;
    private long endPos;
    private boolean kill;
    private OnItemThreadListener mListener;

    DownloadThread(String url, File saveFile, long startPos, long endPos, OnItemThreadListener listener) {
        this.urlStr = url;
        this.localFile = saveFile;
        this.startPos = startPos;
        this.endPos = endPos;
        this.mListener = listener;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            NetUtil.setConnectionProperty(conn, urlStr, startPos, endPos);
            conn.connect();
            if (conn.getResponseCode() == NetUtil.REQUEST_CODE_SUCCESS_PART) {
                InputStream is = conn.getInputStream();
                int len;
                byte[] buf = new byte[4096];

                RandomAccessFile raf = new RandomAccessFile(localFile, "rwd");
                raf.seek(localFile.length());
                while ((len = is.read(buf)) != -1) {
                    if (kill) {
                        break;
                    }
                    raf.write(buf, 0, len);
                    mListener.onLength(threadName(), len);
                }
                raf.close();
                is.close();
                if (!kill) {
                    mListener.onFinish();
                }
            } else {
                mListener.onError(new RuntimeException(threadName() + "---下载出现问题!"));
            }
        } catch (IOException e) {
            mListener.onError(e);
        }
    }

    private String threadName() {
        return Thread.currentThread().getName();
    }

    @Override
    public void kill() {
        kill = true;
    }
}
