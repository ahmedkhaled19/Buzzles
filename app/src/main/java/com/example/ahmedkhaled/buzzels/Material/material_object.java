package com.example.ahmedkhaled.buzzels.Material;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public class material_object {
    private String img_url;
    private String name;
    private String price;
    private boolean is_liked;
    private boolean is_wishlisted;

    public material_object(String img_url, String name, String price, boolean is_liked, boolean is_wishlisted) {
        this.img_url = img_url;
        this.name = name;
        this.price = price;
        this.is_liked = is_liked;
        this.is_wishlisted = is_wishlisted;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public boolean is_liked() {
        return is_liked;
    }

    public boolean is_wishlisted() {
        return is_wishlisted;
    }
}
