package ru.magicwolf.producthuntclient.AsyncRequests;

import android.content.res.Resources;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.magicwolf.producthuntclient.POJOs.GetPostPOJO;
import ru.magicwolf.producthuntclient.POJOs.Post;
import ru.magicwolf.producthuntclient.R;
import ru.magicwolf.producthuntclient.RetrofitAPIs.GetPostAPI;


public class GetPostTask extends AsyncTask<String, Integer, Post>  {
    @Override
    protected Post doInBackground(final String... params) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.producthunt.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetPostAPI api = retrofit.create(GetPostAPI.class);

        Map<String, String> headers = new HashMap<>();

        String authorization = "Bearer " + params[0]; // params[0] - access_token
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", authorization);
        headers.put("Host", "api.producthunt.com");

        Call<GetPostPOJO> call = api.getPost(headers, params[1]); // params[1] - post id

        try {
            return call.execute().body().post;
        } catch (IOException e) {
            e.printStackTrace();
            // Make toast
        }
        return null;
    }
}
