package test.okhttp3;

import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.RequestBody;

public class Helper {

    public static Cookie newCookie() {
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

    public static Headers getHeaders() {
        return new Headers
                .Builder()
                .add("CLIENT_TYPE", "Android")
                .add("CLIENT_VERSION", "30")
                .add("CLIENT_SESSION", "OWEOWEJKSDHGFUIQWYER")
                .build();
    }

    public static RequestBody formUserInfo() {
        return new FormBody
                .Builder()
                .addEncoded("name", "大俊子")
                .add("password", "92347592")
                .add("mobile", "13000000000")
                .build();
    }
}
