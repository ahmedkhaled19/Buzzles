package com.example.ahmedkhaled.buzzels.Material;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public class MaterialObject {

    @SerializedName("id")
    private String id;

    @SerializedName("photo")
    private String img_url;

    @SerializedName("name")
    private String name;

    @SerializedName("is_liked")
    private boolean is_liked;

    @SerializedName("is_wished")
    private boolean is_wishlisted;

    public MaterialObject(String img_url, String name, String id, boolean is_liked, boolean is_wishlisted) {
        this.img_url = img_url;
        this.name = name;
        this.id = id;
        this.is_liked = is_liked;
        this.is_wishlisted = is_wishlisted;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean is_liked() {
        return is_liked;
    }

    public boolean is_wishlisted() {
        return is_wishlisted;
    }

    public void set_liked(boolean is_liked) {
        this.is_liked = is_liked;
    }

    public void set_wishlisted(boolean is_wishlisted) {
        this.is_wishlisted = is_wishlisted;
    }
}
