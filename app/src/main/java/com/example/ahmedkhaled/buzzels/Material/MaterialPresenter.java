package com.example.ahmedkhaled.buzzels.Material;

import org.json.JSONException;

/**
 * Created by AhmedKhaled on 3/30/2017.
 */

public class MaterialPresenter implements MaterialModel.VolleyCallback {

    private MaterialView view;
    private MaterialModel model;

    public MaterialPresenter(MaterialView view, MaterialModel model) {
        this.view = view;
        this.model = model;
    }

    protected void GetData(String id ,int from){
        model.GetSub(this,id,from);
    }

    @Override
    public void onSuccess(String result) throws JSONException {

    }
}
