package test.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitProvider {
    private static final String HOST = "http://a.lrlz.com/";

    static Retrofit provide() {
        return new Retrofit.Builder()
                .baseUrl(HOST)
                .client(OkHttpCreator.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
