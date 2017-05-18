package ru.magicwolf.producthuntclient.RetrofitAPIs;

import android.content.res.Resources;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import ru.magicwolf.producthuntclient.POJOs.GetCategoriesPOJO;
import ru.magicwolf.producthuntclient.R;

import static android.R.attr.value;

public interface GetCategoriesAPI {
    @GET("v1/categories")
    Call<GetCategoriesPOJO> getCategories(@HeaderMap Map<String, String> headers );
}
