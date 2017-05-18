package ru.magicwolf.producthuntclient.POJOs;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPostsInCategoryPOJO {
    @SerializedName("posts")
    @Expose
    public List<Post> posts = null;

}
/*class User {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("headline")
    @Expose
    public String headline;
    @SerializedName("twitter_username")
    @Expose
    public String twitterUsername;
    @SerializedName("website_url")
    @Expose
    public String websiteUrl;
    @SerializedName("profile_url")
    @Expose
    public String profileUrl;
    @SerializedName("image_url")
    @Expose
    public ImageUrl imageUrl;

}*/
