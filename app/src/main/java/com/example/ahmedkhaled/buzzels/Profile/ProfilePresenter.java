package com.example.ahmedkhaled.buzzels.Profile;


import android.util.Log;

import com.example.ahmedkhaled.buzzels.Utils.AppController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.example.ahmedkhaled.buzzels.Utils.URLs.LogOut;


/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public class ProfilePresenter {

    private ProfileView view;
    private ProfileModel model;
    private ProfileObject profile;
    private Observable<String> DataStream;
    private Observable<String> Logout;


    public ProfilePresenter(ProfileView view, ProfileModel model) {
        this.view = view;
        this.model = model;
        DataStream = model.GetProfile();

    }

    protected void GetData() {
        DataStream.observeOn(Schedulers.io())
                .map(new Function<String, ProfileObject>() {
                    @Override
                    public ProfileObject apply(String query) throws JSONException {
                        JSONObject res = new JSONObject(query);
                        JSONObject Data = res.getJSONObject("data");
                        GsonBuilder builder = new GsonBuilder();
                        Gson mGson = builder.create();
                        profile = mGson.fromJson(String.valueOf(Data), ProfileObject.class);
                        return profile;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProfileObject>() {
                    @Override
                    public void accept(ProfileObject profile) {
                        view.Name(profile.getName());
                        view.Job(profile.getJob());
                        view.Profile(profile.getImage());
                    }
                });
    }


    public void GoToAnalysis() {
        view.StartAnalysis();
    }

    protected void GoToWishlist() {
        view.StartWishlist();
    }

    protected void LogOut() {
        Logout = model.LogOut();
        Logout.observeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String query) throws JSONException {
                        AppController.getInstance().logout();
                        return "logout";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String profile) {
                        view.LogOut();
                    }
                });
    }

}
