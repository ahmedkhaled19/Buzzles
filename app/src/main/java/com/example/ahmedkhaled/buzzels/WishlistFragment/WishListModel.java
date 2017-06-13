package com.example.ahmedkhaled.buzzels.WishlistFragment;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ahmedkhaled.buzzels.Utils.AppController;
import com.example.ahmedkhaled.buzzels.Utils.URLs;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by AhmedKhaled on 3/29/2017.
 */

public class WishListModel {

    protected Observable<String> GetMyWish() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                StringRequest stringRequest =
                        new StringRequest(Request.Method.GET, URLs.User_WishList,
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

                                });
                AppController.getInstance().addToRequestQueue(stringRequest);
            }
        });
    }

    protected Observable<String> UnWishlist(final String id) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                String URL_UNWish = URLs.UnWishList + id;
                StringRequest stringRequest =
                        new StringRequest(Request.Method.POST, URL_UNWish,
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
                                params.put("material_id", id);
                                params.put("token", AppController.getInstance().UserKey());
                                return params;
                            }
                        };
                AppController.getInstance().addToRequestQueue(stringRequest);
            }
        });
    }
}
