package ru.magicwolf.producthuntclient.POJOs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnail {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("media_type")
    @Expose
    public String mediaType;
    @SerializedName("image_url")
    @Expose
    public String imageUrl;

}
