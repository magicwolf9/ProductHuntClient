package ru.magicwolf.producthuntclient.POJOs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPostPOJO {
    @SerializedName("post")
    @Expose
    public Post post;
}

