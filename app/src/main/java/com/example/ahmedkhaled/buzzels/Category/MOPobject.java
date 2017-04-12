package com.example.ahmedkhaled.buzzels.Category;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AhmedKhaled on 3/28/2017.
 */

public class MOPobject {

    @SerializedName("id")
    private String id;

    @SerializedName("photo")
    private String imageURL;

    @SerializedName("name")
    private String name;


    private int type;

    public MOPobject(String id, String imageURL, String name) {
        this.id = id;
        this.imageURL = imageURL;
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getImageURL() {
        return imageURL.replace(" ", "%20");
    }

    public String getName() {
        return name;
    }
}
