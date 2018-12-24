package test.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import test.util.LogUtil;

import java.io.IOException;


public class NetWorkTest {

    public static void request() {
        Retrofit retrofit = RetrofitProvider.provide();
        BlogService blogService = retrofit.create(BlogService.class);
        Call<ResponseBody> specialCall = blogService.getSpecialByQuery(186);
        specialCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    LogUtil.log(result);
                } catch (IOException e) {
                    LogUtil.log(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                LogUtil.log(throwable.getMessage());
            }
        });
    }
}
