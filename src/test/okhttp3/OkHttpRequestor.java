package test.okhttp3;

import okhttp3.*;
import sun.security.ssl.SSLSocketFactoryImpl;
import test.utils.LogUtil;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class OkHttpRequestor {

    private OkHttpRequestor() {
    }

    private static final class InstanceHolder {
        private static final OkHttpRequestor Instance = new OkHttpRequestor();
    }

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
            if (!mRequested) {
                request(URL_BAI_DU);
            }
            mRequested = true;
            LogUtil.log("onResponse :--->" + response.body().string());
        }
    };

    public static void test() {
        InstanceHolder.Instance.request(URL_TABS);
    }

    private void request(String url) {
        Request request = createRequest(url);
        mClient.newCall(request).enqueue(mCallBack);
    }

    private Request createRequest(String url) {
        return new Request
                .Builder()
                .url(url)
//                .headers(getHeaders())
//                .put(formBody())
                .build();
    }

    private RequestBody requestBody() {
        return RequestBody.create(MultipartBody.MIXED, new File(""));
    }

    private RequestBody formBody() {
        return new FormBody
                .Builder()
                .addEncoded("name", "大俊子")
                .add("password", "92347592")
                .add("mobile", "13000000000")
                .build();
    }

    private Headers getHeaders() {
        return new Headers
                .Builder()
                .add("CLIENT_TYPE", "Android")
                .add("CLIENT_VERSION", "30")
                .add("CLIENT_SESSION", "OWEOWEJKSDHGFUIQWYER")
                .build();
    }

    private Cookie cookie() {
        return new Cookie
                .Builder()
                .domain("a.lrlz.com")
                .expiresAt(23512348590L)
                .secure()
                .name("wujun")
                .value("hqweiu")
                .httpOnly()
                .path("/aksdfiu/asdfh")
                .build();
    }
}
