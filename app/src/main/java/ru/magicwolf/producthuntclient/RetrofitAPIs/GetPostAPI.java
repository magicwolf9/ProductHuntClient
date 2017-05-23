package ru.magicwolf.producthuntclient.RetrofitAPIs;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;
import ru.magicwolf.producthuntclient.POJOs.GetPostPOJO;

public interface GetPostAPI {
    @GET
    Call<GetPostPOJO> getPost(@HeaderMap Map<String, String> headers, @Url String url);
}
