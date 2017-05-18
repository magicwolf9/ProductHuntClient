package ru.magicwolf.producthuntclient.POJOs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScreenshotUrl {

    @SerializedName("300px")
    @Expose
    public String _300px;
    @SerializedName("850px")
    @Expose
    public String _850px;

}
