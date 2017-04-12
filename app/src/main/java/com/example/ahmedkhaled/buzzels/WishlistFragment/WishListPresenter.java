package com.example.ahmedkhaled.buzzels.WishlistFragment;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AhmedKhaled on 3/29/2017.
 */

public class WishListPresenter implements WishListModel.VolleyCallback {

    private WishListView view;
    private WishListModel model;

    public WishListPresenter(WishListView view, WishListModel model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void onSuccess(String result) throws JSONException {
        JSONObject res = new JSONObject(result);
        JSONArray thread = res.getJSONArray("data");
        for (int i = 0; i < thread.length(); i++) {
            JSONObject obj = thread.getJSONObject(i);
            /*MOPobject object = new MOPobject(obj.getString("id"),
                    obj.getString("photo").replace(" ", "%20"));
            MOP.add(object);
        }
        view.setData(MOP);
        SetDots(0,MOP.size());*/
        }
    }
}
