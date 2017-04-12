package com.example.ahmedkhaled.buzzels.SignUp;

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
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public class SignUpModel {

    private String Res = "null";

    protected String StepOne(final VolleyCallback callback, final String username, final String password) {
        StringRequest stringRequest =
                new StringRequest(Request.Method.POST, URLs.Register,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    callback.onSuccess(response, 1);
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
                        params.put("username", username);
                        params.put("password", password);
                        params.put("step", "1");
                        params.put("token", Constants.Developer_Key);
                        return params;
                    }
                };
        AppController.getInstance().addToRequestQueue(stringRequest);
        return Res;
    }

    public interface VolleyCallback {
        void onSuccess(String result, int step) throws JSONException;
    }


    public void StepTwo(final VolleyCallback callback , final String fullname
            , final String dob , final String mail , final String country , final String job , final String gander ) {
        StringRequest stringRequest =
                new StringRequest(Request.Method.POST, URLs.Register,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    callback.onSuccess(response,2);
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
                        params.put("fullname",fullname);
                        params.put("dob", dob);
                        params.put("email", mail);
                        params.put("country_id", country );
                        params.put("jop_title_id", job);
                        params.put("gender", gander);
                        params.put("step", "2");
                        params.put("token", AppController.getInstance().UserKey());
                        return params;
                    }
                };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
