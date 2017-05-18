package ru.magicwolf.producthuntclient.RetrofitAPIs;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import ru.magicwolf.producthuntclient.POJOs.GetPostPOJO;

public interface GetPostAPI {

    @GET("v1/posts/")
    Call<GetPostPOJO> getPost(@HeaderMap Map<String, String> headers, @Field("") String id);
}
