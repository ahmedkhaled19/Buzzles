package com.example.ahmedkhaled.buzzels.Collection;

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
 * Created by AhmedKhaled on 3/21/2017.
 */

public class CollectionPresenter implements CollectionModel.VolleyCallback {

    private CollectionView view;
    private CollectionModel model;
    private List<MOPobject> MOP = new ArrayList<>();

    public CollectionPresenter(Collection view, CollectionModel model) {
        this.view = view;
        this.model = model;
    }

    protected void GetCollection() {
        model.GetCollection(this);
    }

    @Override
    public void onSuccess(String result) throws JSONException {
        JSONObject res = new JSONObject(result);
        JSONArray thread = res.getJSONArray("data");
       /* for (int i = 0; i < thread.length(); i++) {
            JSONObject obj = thread.getJSONObject(i);
            MOPobject object = new MOPobject(obj.getString("id"),
                    obj.getString("photo").replace(" ", "%20"));
            MOP.add(object);
        }*/
        GsonBuilder builder = new GsonBuilder();
        Gson mGson = builder.create();
        MOP = Arrays.asList(mGson.fromJson(String.valueOf(thread), MOPobject[].class));
        view.SetData(MOP);
    }

    public void onclick(int position) {
        view.Start(MOP.get(position).getId());
    }
}
