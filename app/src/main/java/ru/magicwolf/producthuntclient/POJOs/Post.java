package ru.magicwolf.producthuntclient.POJOs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("category_id")
    @Expose
    public Integer categoryId;
    @SerializedName("day")
    @Expose
    public String day;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("tagline")
    @Expose
    public String tagline;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("discussion_url")
    @Expose
    public String discussionUrl;
    @SerializedName("redirect_url")
    @Expose
    public String redirectUrl;
    @SerializedName("screenshot_url")
    @Expose
    public ScreenshotUrl screenshotUrl;
    @SerializedName("thumbnail")
    @Expose
    public Thumbnail thumbnail;
    @SerializedName("votes_count")
    @Expose
    public Integer votesCount;

}
