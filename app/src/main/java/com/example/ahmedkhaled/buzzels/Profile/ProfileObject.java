package com.example.ahmedkhaled.buzzels.Profile;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AhmedKhaled on 4/7/2017.
 */

public class ProfileObject {

    @SerializedName("username")
    String name;

    @SerializedName("image")
    String image;

    @SerializedName("jop")
    String job;

    public ProfileObject(String image, String name, String job) {
        this.image = image;
        this.name = name;
        this.job = job;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
