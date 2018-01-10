package test.retrofit;

import okhttp3.*;


class OkHttpCreator {
    static OkHttpClient create() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(chain -> chain.proceed(chain.request()
                        .newBuilder()
                        .addHeader("type", "android")
                        .addHeader("member_id", "893792").build()))
                .build();
    }
}
