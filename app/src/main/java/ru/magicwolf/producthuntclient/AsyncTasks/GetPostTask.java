package ru.magicwolf.producthuntclient.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.magicwolf.producthuntclient.POJOs.GetPostPOJO;
import ru.magicwolf.producthuntclient.POJOs.Post;
import ru.magicwolf.producthuntclient.RetrofitAPIs.GetPostAPI;


public class GetPostTask extends AsyncTask<String, Integer, Post>  {
    @Override
    protected Post doInBackground(final String... params) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.producthunt.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetPostAPI api = retrofit.create(GetPostAPI.class);

        Map<String, String> headers = new HashMap<>();

        String authorization = "Bearer " + params[0]; // params[0] - access_token
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", authorization);
        headers.put("Host", "api.producthunt.com");

        String url = "v1/posts/" + params[1];
        Call<GetPostPOJO> call = api.getPost(headers, url); // params[1] - post id

        try {
            return call.execute().body().post;
        } catch (IOException e) {
            Log.i("INFO", "I wont be there");
            e.printStackTrace();
            // Make toast
        }
        return null;
    }
}
