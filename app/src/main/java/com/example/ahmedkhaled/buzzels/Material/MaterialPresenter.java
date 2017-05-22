package com.example.ahmedkhaled.buzzels.Material;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.ImageView;

import com.example.ahmedkhaled.buzzels.Category.MOPobject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static java.lang.System.out;

/**
 * Created by AhmedKhaled on 3/30/2017.
 */

public class MaterialPresenter {

    private MaterialView view;
    private MaterialModel model;
    private Observable<String> data;
    private List<MaterialObject> MO;

    public MaterialPresenter(MaterialView view, MaterialModel model, String id, int from) {
        this.view = view;
        this.model = model;
        data = this.model.GetSub(id, from);
    }

    protected void GetData() {
        data.observeOn(Schedulers.computation())
                .map(new Function<String, List<MaterialObject>>() {
                    @Override
                    public List<MaterialObject> apply(String query) throws JSONException {
                        JSONObject res = new JSONObject(query);
                        JSONArray thread = res.getJSONArray("data");
                        GsonBuilder builder = new GsonBuilder();
                        Gson mGson = builder.create();
                        MO = Arrays.asList(mGson.fromJson(String.valueOf(thread), MaterialObject[].class));
                        return MO;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MaterialObject>>() {
                    // will be called when the observable emits an item
                    @Override
                    public void accept(List<MaterialObject> Data) {
                        view.putdata(Data);

                    }
                });
    }

    protected boolean Like(String id) {
        final boolean[] flag = {false};
        Observable<String> Like = model.Like(id);
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
                        Log.d("ahmeddd", String.valueOf(flage));
                        flag[0] = flage;
                    }
                });
        return flag[0];
    }

    protected Boolean Unlike(String id) {
        final boolean[] flag = {false};
        Observable<String> Like = model.UnLike(id);
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
                        Log.d("ahmeddd", String.valueOf(flage));
                        flag[0] = flage;
                    }
                });
        return flag[0];
    }

    protected boolean Wish(String id) {
        final boolean[] flag = {false};
        Observable<String> Like = model.Wish(id);
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
                        flag[0] = flage;
                    }
                });
        return flag[0];
    }

    protected Boolean Unwish(String id) {
        final boolean[] flag = {false};
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
                        Log.d("ahmeddd", String.valueOf(flage));
                        flag[0] = flage;
                    }
                });
        return flag[0];
    }


}
