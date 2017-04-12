package com.example.ahmedkhaled.buzzels.SubCategory;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AhmedKhaled on 3/29/2017.
 */

public class SubObject {

    @SerializedName("id")
    private String id;

    @SerializedName("photo")
    private String image;

    public SubObject(String id, String image) {
        this.id = id;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}
