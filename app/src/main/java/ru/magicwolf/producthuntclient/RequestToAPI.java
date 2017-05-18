package ru.magicwolf.producthuntclient;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.magicwolf.producthuntclient.AsyncRequests.GetCategoriesTask;
import ru.magicwolf.producthuntclient.AsyncRequests.GetPostTask;
import ru.magicwolf.producthuntclient.AsyncRequests.GetPostsInCategoryTask;
import ru.magicwolf.producthuntclient.POJOs.Category;
import ru.magicwolf.producthuntclient.POJOs.Post;

public class RequestToAPI {

    public List<Category> LoadCategoriesList(){
        GetCategoriesTask getCategoriesTask = new GetCategoriesTask();
        try {
            return getCategoriesTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            //Make toast
        }
        return null;
    }
    public List<Post> LoadPostsInCategory(){
        GetPostsInCategoryTask getPostsInCategoryTask = new GetPostsInCategoryTask();
        try {
            return getPostsInCategoryTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            //Make toast
        }
        return null;
    }
    public Post LoadPostInfo(){
        GetPostTask getPostTask = new GetPostTask();
        try {
            return getPostTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            //Make toast
        }
        return null;
    }
}
