package test.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BlogService {

    @GET("index.php?act=special&op=index&page=10&curpage=1&client_type=ios")
    Call<ResponseBody> getSpecialByQuery(@Query("special_id") int special_id);
}
