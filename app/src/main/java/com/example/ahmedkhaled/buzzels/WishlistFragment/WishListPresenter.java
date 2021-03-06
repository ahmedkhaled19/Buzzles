package com.example.ahmedkhaled.buzzels.WishlistFragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.ImageView;

import com.example.ahmedkhaled.buzzels.Material.MaterialObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.data;

/**
 * Created by AhmedKhaled on 3/29/2017.
 */

public class WishListPresenter {

    private WishListView view;
    private WishListModel model;
    private Observable<String> Data;
    private List<MaterialObject> MyWish;


    public WishListPresenter(WishListView view, WishListModel model) {
        this.view = view;
        this.model = model;
        Data = this.model.GetMyWish();
    }


    protected void GetWIsh() {
        Data.observeOn(Schedulers.computation())
                .map(new Function<String, List<MaterialObject>>() {
                    @Override
                    public List<MaterialObject> apply(String query) throws JSONException {
                        JSONObject res = new JSONObject(query);
                        JSONArray thread = res.getJSONArray("data");
                        GsonBuilder builder = new GsonBuilder();
                        Gson mGson = builder.create();
                        MyWish = new LinkedList(Arrays.asList(mGson.fromJson(String.valueOf(thread), MaterialObject[].class)));
                        return MyWish;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MaterialObject>>() {
                    // will be called when the observable emits an item
                    @Override
                    public void accept(List<MaterialObject> Data) {
                        view.PutData(MyWish);

                    }
                });
    }

    public void Unwish(String id) {
        Observable<String> Like = model.UnWishlist(id);
        Like.observeOn(Schedulers.computation())
                .map(new Function<String, Boolean>() {
                    @Override
                    public Boolean apply(String respose) throws Exception {
                        JSONObject object = new JSONObject(respose);
                        String statue = object.getString("status");
                        if (statue.equals("false")) {
                            return false;

                        } else {
                            return true;
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean flage) throws Exception {
                    }
                });

    }

}
