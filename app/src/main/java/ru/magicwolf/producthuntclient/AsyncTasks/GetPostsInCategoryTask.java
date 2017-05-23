package ru.magicwolf.producthuntclient.AsyncTasks;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.magicwolf.producthuntclient.POJOs.GetPostsInCategoryPOJO;
import ru.magicwolf.producthuntclient.POJOs.Post;
import ru.magicwolf.producthuntclient.RetrofitAPIs.PostsInCategoryAPI;


public class GetPostsInCategoryTask extends AsyncTask<String, Integer, List<Post>>  {
    @Override
    protected List<Post> doInBackground(String... params) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.producthunt.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostsInCategoryAPI api = retrofit.create(PostsInCategoryAPI.class);

        Map<String, String> headers = new HashMap<>();

        String authorization = "Bearer " + params[0]; //params[0] - access_token
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", authorization);
        headers.put("Host", "api.producthunt.com");

        String url = "v1/categories/" + params[1] + "/posts";
        Call<GetPostsInCategoryPOJO> call = api.getPosts(headers, url); //param[1] - категория, для которой нужно получиться новости


        try {
            return call.execute().body().posts;
        } catch (IOException e) {
            e.printStackTrace();
            //Make toast
        }
        return null;
    }
}
