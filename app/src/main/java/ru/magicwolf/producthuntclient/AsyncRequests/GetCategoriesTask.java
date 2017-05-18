package ru.magicwolf.producthuntclient.AsyncRequests;

import android.content.res.Resources;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.magicwolf.producthuntclient.POJOs.Category;
import ru.magicwolf.producthuntclient.POJOs.GetCategoriesPOJO;
import ru.magicwolf.producthuntclient.R;
import ru.magicwolf.producthuntclient.RetrofitAPIs.GetCategoriesAPI;

public class GetCategoriesTask extends AsyncTask<String, Integer, List<Category>>  {
    @Override
    protected List<Category> doInBackground(final String... params) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("api.producthunt.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetCategoriesAPI api = retrofit.create(GetCategoriesAPI.class);

        Map<String, String> headers = new HashMap<>();

        String authorization = "Bearer " + Resources.getSystem().getString(R.string.acсess_token);
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", authorization);
        headers.put("Host", "api.producthunt.com");

        Call<GetCategoriesPOJO> call = api.getCategories(headers);

        try {
            return call.execute().body().categories;
        } catch (IOException e) {
            e.printStackTrace();
            //Make toast
        }
        return null;
    }
}