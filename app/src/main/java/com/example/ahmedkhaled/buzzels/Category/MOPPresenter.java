package com.example.ahmedkhaled.buzzels.Category;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public class MOPPresenter {

    private MOPModel mopModel;
    private MOPView view;
    private List<MOPobject> MOP;
    private Observable<String> response;
    private int Type;

    public MOPPresenter(MOPModel mopModel, MOPView view, String step) {
        this.mopModel = mopModel;
        this.view = view;
        Type = Integer.valueOf(step);
        response = this.mopModel.GetMaterial(step);
    }

    protected void GetMaterial() {
        response.observeOn(Schedulers.computation())
                .map(new Function<String, List<MOPobject>>() {
                    @Override
                    public List<MOPobject> apply(String query) throws JSONException {
                        JSONObject res = new JSONObject(query);
                        JSONArray thread = res.getJSONArray("data");
                        GsonBuilder builder = new GsonBuilder();
                        Gson mGson = builder.create();
                        MOP = Arrays.asList(mGson.fromJson(String.valueOf(thread), MOPobject[].class));
                        for (int i = 0; i < MOP.size(); i++) {
                            if (Type == 1) {
                                MOP.get(i).setType(1);
                            } else if (Type == 3 && MOP.get(i).getName().equals("Printed")) {
                                MOP.get(i).setType(1);
                            } else {
                                MOP.get(i).setType(2);
                            }
                        }
                        return MOP;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MOPobject>>() {
                    // will be called when the observable emits an item
                    @Override
                    public void accept(List<MOPobject> MOP) {
                        view.setData(MOP);
                        SetDots(0, MOP.size());
                    }
                });

    }

    protected void SetDots(int pastVisiblesItems, int size) {
        view.setupDots(pastVisiblesItems, size);
    }

}
