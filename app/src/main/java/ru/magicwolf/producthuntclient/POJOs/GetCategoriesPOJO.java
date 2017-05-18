package ru.magicwolf.producthuntclient.POJOs;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCategoriesPOJO {
    @SerializedName("categories")
    @Expose
    public List<Category> categories = null;

}


