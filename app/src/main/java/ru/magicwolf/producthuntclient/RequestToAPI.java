package ru.magicwolf.producthuntclient;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.magicwolf.producthuntclient.AsyncTasks.GetCategoriesTask;
import ru.magicwolf.producthuntclient.AsyncTasks.GetPostsInCategoryTask;
import ru.magicwolf.producthuntclient.POJOs.Category;
import ru.magicwolf.producthuntclient.POJOs.Post;

public class RequestToAPI {

    public List<Category> LoadCategoriesList(String token){
        GetCategoriesTask getCategoriesTask = new GetCategoriesTask();
        try {
            return getCategoriesTask.execute(token).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            //Make toast
        }
        return null;
    }
    public List<Post> LoadPostsInCategory(String token, String category ){
        GetPostsInCategoryTask getPostsInCategoryTask = new GetPostsInCategoryTask();
        try {
            return getPostsInCategoryTask.execute(token, category).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            //Make toast
        }
        return null;
    }
}
