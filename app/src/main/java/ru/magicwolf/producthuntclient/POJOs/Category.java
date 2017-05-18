package ru.magicwolf.producthuntclient.POJOs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("color")
    @Expose
    public String color;
    @SerializedName("item_name")
    @Expose
    public String itemName;

}
