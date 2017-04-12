package com.example.ahmedkhaled.buzzels.SubCategory;

import android.util.Log;

import com.example.ahmedkhaled.buzzels.Category.MOPobject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AhmedKhaled on 3/29/2017.
 */

public class SubCategoryPresenter implements SubCategoryModel.VolleyCallback {

    private SubCategoryModel model;
    private SubCategoryView view;
    private List<SubObject> objects = new ArrayList<>();

    public SubCategoryPresenter(SubCategoryModel model, SubCategoryView view) {
        this.model = model;
        this.view = view;
    }

    protected void GetData(String id) {
        model.GetSub(this, id);
    }

    @Override
    public void onSuccess(String result) throws JSONException {
        JSONObject res = new JSONObject(result);
        JSONArray thread = res.getJSONArray("data");
        GsonBuilder builder = new GsonBuilder();
        Gson mGson = builder.create();
        objects = Arrays.asList(mGson.fromJson(String.valueOf(thread), SubObject[].class));
        view.SetData(objects);
        SetDots(0, objects.size());
    }

    protected void SetDots(int pastVisiblesItems, int size) {
        view.SetDots(size, pastVisiblesItems);
    }

}
