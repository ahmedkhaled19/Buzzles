package com.example.ahmedkhaled.buzzels.Utils;


public class URLs {

    public static final String ROOT_URL = "http://api.itsgd.org/index.php/";
    public static final String Register = ROOT_URL + "register";
    public static final String Countries = ROOT_URL + "req/get?table=apps_countries&token=" + Constants.Developer_Key;
    public static final String Jobs = ROOT_URL + "req/get?table=job_titles&token=" + Constants.Developer_Key;
    public static final String Login = ROOT_URL + "login";
    public static final String Profile = ROOT_URL + "profile";
    public static final String Category = ROOT_URL + "category";
    public static final String SubCategory = ROOT_URL + "category/";
    public static final String Material = ROOT_URL + "materials";
    public static final String Collection = ROOT_URL + "category/collections";
    public static final String User_WishList = ROOT_URL + "materials/wishlist/get?token=" + AppController.getInstance().UserKey();
    public static final String UnWishList = ROOT_URL + "materials/removeFromWishlist/";
    public static final String AddToWishlist = ROOT_URL + "materials/addToWishlish/";
    public static final String Like = ROOT_URL + "materials/like/";
    public static final String Unlike = ROOT_URL + "materials/unlike/";
    public static final String LogOut = ROOT_URL + "logout";

}
