package test.thread;

import test.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mac on 2017/9/29.
 */
public class DownloadThreadPro extends Thread {
    private static final long BREAKPOINT_POS = 4096;
    private String urlStr;
    private File localFile;
    private Long startPos;
    private Long endPos;
    private RandomAccessFileListener mListener;

    public DownloadThreadPro(String url, File saveFile, Long startPos, Long endPos, RandomAccessFileListener listener) {
        this.urlStr = url;
        this.localFile = saveFile;
        this.startPos = startPos;
        this.endPos = endPos;
        this.mListener = listener;
    }

    @Override
    public void run() {
        mListener.onStart(Thread.currentThread().getName() + "---碎片化下载部分文件,文件名为: " + localFile.getName());
        mListener.onDownloading(Thread.currentThread().getName() + "---开始下载...节点为:" + startPos + " : " + endPos);
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            NetUtil.setConnectionProperty(conn, urlStr, startPos, endPos);
            conn.connect();
            if (conn.getResponseCode() == NetUtil.REQUEST_CODE_SUCCESS_PART) {
                mListener.onDownloading(Thread.currentThread().getName() + "---连接成功...");
                InputStream is = conn.getInputStream();
                int len;
                boolean breaked = false;
                byte[] buf = new byte[4096];

                RandomAccessFile raf = new RandomAccessFile(localFile, "rwd");
                raf.seek(localFile.length());
                while ((len = is.read(buf)) != -1) {
                    raf.write(buf, 0, len);
                    mListener.onDownloading(Thread.currentThread().getName() + "---下载ing..." + localFile.length());
                    if (localFile.length() == BREAKPOINT_POS) {
                        mListener.onPaused(Thread.currentThread().getName() + "---中断开始...");
                        breaked = true;
                        break;
                    }
                }
                raf.close();
                is.close();
                if (breaked) {
                    mListener.onPaused(Thread.currentThread().getName() + "---下载中断  ： " + startPos + " -- " + localFile.length());
                } else {
                    mListener.onFinish(Thread.currentThread().getName() + "---完成下载  ： " + startPos + " -- " + endPos);
                }
            } else {
                mListener.onError(new RuntimeException(Thread.currentThread().getName() + "---下载出现问题!"));
            }
        } catch (IOException e) {
            mListener.onError(e);
            e.printStackTrace();
        }
    }

}
