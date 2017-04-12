package com.example.ahmedkhaled.buzzels.Profile;

import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ahmedkhaled.buzzels.Utils.AppController;
import com.example.ahmedkhaled.buzzels.Utils.URLs;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public class ProfileModel {


    protected Observable<String> GetProfile() {

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                StringRequest stringRequest =
                        new StringRequest(Request.Method.POST, URLs.Profile,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        emitter.onNext(response);
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
                                params.put("token", AppController.getInstance().UserKey());
                                return params;
                            }
                        };
                AppController.getInstance().addToRequestQueue(stringRequest);
            }
        })
        .observeOn(Schedulers.newThread());
    }

}
