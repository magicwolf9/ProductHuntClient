package ru.magicwolf.producthuntclient.AsyncTasks;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.magicwolf.producthuntclient.POJOs.Category;
import ru.magicwolf.producthuntclient.POJOs.GetCategoriesPOJO;
import ru.magicwolf.producthuntclient.RetrofitAPIs.GetCategoriesAPI;

public class GetCategoriesTask extends AsyncTask<String, Integer, List<Category>>  {
    @Override
    protected List<Category> doInBackground(final String... params) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.producthunt.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetCategoriesAPI api = retrofit.create(GetCategoriesAPI.class);

        Map<String, String> headers = new HashMap<>();

        String authorization = "Bearer " + params[0]; //params[0] = access_token
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
