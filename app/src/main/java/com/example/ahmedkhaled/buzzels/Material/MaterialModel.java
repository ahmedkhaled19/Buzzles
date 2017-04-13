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

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by AhmedKhaled on 3/30/2017.
 */

public class MaterialModel {

    protected Observable<String> GetSub(final String id, final int from) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                StringRequest stringRequest =
                        new StringRequest(Request.Method.POST, URLs.Material,
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
        });
    }

    protected Observable<String> Like(final String id) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                StringRequest stringRequest =
                        new StringRequest(Request.Method.POST, URLs.Like,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d("ahmeddd",response);
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

    protected Observable<String> UnLike(final String id) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                StringRequest stringRequest =
                        new StringRequest(Request.Method.POST, URLs.Unlike,
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

    protected Observable<String> Wish(final String id) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                StringRequest stringRequest =
                        new StringRequest(Request.Method.POST, URLs.AddToWishlist,
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

    protected Observable<String> UnWishlist(final String id) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                StringRequest stringRequest =
                        new StringRequest(Request.Method.POST, URLs.UnWishList,
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
