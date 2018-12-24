package test.okhttp3;

import okhttp3.*;
import test.util.LogUtil;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class OkHttpRequestor {

    private OkHttpRequestor() {
    }

    private static final class InstanceHolder {
        private static final OkHttpRequestor Instance = new OkHttpRequestor();
    }

    private static final String FILE_PATH = "/Users/mac/Desktop/20180115.png";
    private static final String URL_BAI_DU = "http://www.baidu.com";
    private static final String URL_SPECIAL = "http://a.lrlz.com/mobile/index.php?act=special&op=index&special_id=186&page=10&curpage=1&client_type=android";
    private static final String URL_TABS = "http://a.lrlz.com/mobile/index.php?act=index&op=tabs&client_type=android";
    private boolean mRequested = false;

    private ConcurrentHashMap<HttpUrl, List<Cookie>> mCookies = new ConcurrentHashMap<>();
    private OkHttpClient mClient = new OkHttpClient
            .Builder()
            .authenticator(new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    return response
                            .newBuilder()
                            .addHeader("CLIENT_SESSION", "XXJHSFWE")
                            .build()
                            .request();
                }
            })
            .cookieJar(new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                    mCookies.put(httpUrl, list);
                    LogUtil.log("saveFromResponse :--->httpUrl=" + httpUrl + "---->List<Cookie>=" + list.toString());
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                    return mCookies.get(httpUrl) == null ? Collections.EMPTY_LIST : mCookies.get(httpUrl);
                }
            })
            .addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("CLIENT_TYPE", "Android");
                    builder.addHeader("CLIENT_VERSION", String.valueOf(30));
                    return chain.proceed(builder.build());
                }
            })
            .hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            })
            .readTimeout(5, TimeUnit.SECONDS)
            .build();

    private Callback mCallBack = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            LogUtil.log("onFailure :--->" + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            LogUtil.log("onResponse :--->" + response.body().string());
            if (!mRequested) {
//                requestNormal(URL_BAI_DU);
            }
            mRequested = true;
        }
    };

    public static void test() {
        InstanceHolder.Instance.requestNormal(URL_TABS);
    }

    public static void testUploadFile() {
        InstanceHolder.Instance.createUploadFileRequest(FILE_PATH);
    }

    private void createUploadFileRequest(String filePath) {
        Request request = new Request
                .Builder()
                .url("http://a.lrlz.com/upfile.php?act=\"\"op=\"\"&client_type=android")
                .post(createUploadFileRequestBody(new File(filePath)))
                .build();

        mClient.newCall(request).enqueue(mCallBack);
    }

    private void requestNormal(String url) {
        Request request = createRequest(url);
        mClient.newCall(request).enqueue(mCallBack);
    }

    private Request createRequest(String url) {
        return new Request
                .Builder()
                .url(url)
                .build();
    }

    private RequestBody createUploadFileRequestBody(File file) {
        return new MultipartBody
                .Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("submit", "Upload")
                .addFormDataPart("file", "image.png", RequestBody.create(MultipartBody.FORM, file))
                .build();
    }
}
