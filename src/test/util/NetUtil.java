package test.util;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by mac on 2017/9/29.
 */

public class NetUtil {

    public static final int REQUEST_CODE_SUCCESS = 200;
    public static final int REQUEST_CODE_SUCCESS_PART = 206;

    public static void setConnectionProperty(HttpURLConnection conn, String url, long startPos, long endPos) throws IOException {
        conn.setConnectTimeout(15 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty(
                "Accept",
                "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Accept-Encoding", "identity");
        conn.setRequestProperty("Referer", url);
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty(
                "User-Agent",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
        conn.setRequestProperty("Connection", "Keep-Alive");
        if (endPos != 0) {
            conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);// 设置获取实体数据的范围
        }
    }
}
