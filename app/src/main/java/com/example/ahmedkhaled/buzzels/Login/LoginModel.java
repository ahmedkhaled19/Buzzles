package com.example.ahmedkhaled.buzzels.Login;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
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
 * Created by AhmedKhaled on 3/21/2017.
 */

public class LoginModel {

    protected void LogIN(final VolleyCallback callback, final String username, final String password) {
        StringRequest stringRequest =
                new StringRequest(Request.Method.POST, URLs.Login,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    callback.onSuccess(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                NetworkResponse networkResponse = error.networkResponse;
                                try {
                                    callback.onSuccess(new String(networkResponse.data));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", username);
                        params.put("password", password);
                        params.put("token", Constants.Developer_Key);
                        return params;
                    }
                };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    public interface VolleyCallback {
        void onSuccess(String result) throws JSONException;
    }
}

