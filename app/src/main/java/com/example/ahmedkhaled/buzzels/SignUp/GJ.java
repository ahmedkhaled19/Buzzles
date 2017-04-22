package com.example.ahmedkhaled.buzzels.SignUp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AhmedKhaled on 4/20/2017.
 */

public class GJ {

    @SerializedName("country_name")
    private String CountryName;

    @SerializedName("title")
    private Object title;

    @SerializedName("id")
    private String id;

    public GJ(String CountryName, String id) {
        this.CountryName = CountryName;
        this.id = id;
    }

    public GJ(Object title, String id) {
        this.title = String.valueOf(title);
        this.id = id;
    }

    protected String getCountryName() {
        return CountryName;
    }

    protected String getId() {
        return id;
    }

    public Object getTitle() {
        return title;
    }
}
