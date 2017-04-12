package com.example.ahmedkhaled.buzzels.Material;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ahmedkhaled.buzzels.Utils.AppController;
import com.example.ahmedkhaled.buzzels.Utils.Constants;
import com.example.ahmedkhaled.buzzels.Utils.URLs;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AhmedKhaled on 3/30/2017.
 */

public class MaterialModel {

    protected void GetSub(final VolleyCallback callback, final String id, final int from) {
        StringRequest stringRequest =
                new StringRequest(Request.Method.POST, URLs.Material,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    Log.d("ahmeddd", response);
                                    callback.onSuccess(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        if (from == 1) {
                            params.put("category_id", id);
                        } else if (from == 2) {
                            params.put("subcategory_id", id);
                        } else if (from == 3) {
                            params.put("collection_id", id);
                        }
                        params.put("token", AppController.getInstance().UserKey());
                        return params;
                    }
                };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public interface VolleyCallback {
        void onSuccess(String result) throws JSONException;
    }

}
