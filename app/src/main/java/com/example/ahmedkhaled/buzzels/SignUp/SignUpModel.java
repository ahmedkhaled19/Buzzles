package com.example.ahmedkhaled.buzzels.SignUp;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ahmedkhaled.buzzels.Utils.AppController;
import com.example.ahmedkhaled.buzzels.Utils.Constants;
import com.example.ahmedkhaled.buzzels.Utils.ProfileRepo;
import com.example.ahmedkhaled.buzzels.Utils.URLs;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public class SignUpModel {

    protected void StepOne(final VolleyCallback callback, final String username, final String password) {
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
                                NetworkResponse networkResponse = error.networkResponse;
                                try {
                                    callback.onSuccess(new String(networkResponse.data), 1);
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
                        params.put("step", "1");
                        params.put("token", Constants.Developer_Key);
                        return params;
                    }
                };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    protected void StepTwo(final VolleyCallback callback, final String fullname
            , final String dob, final String mail, final String country, final String job, final String gander) {
        StringRequest stringRequest =
                new StringRequest(Request.Method.POST, URLs.Register,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    callback.onSuccess(response, 2);
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
                                    callback.onSuccess(new String(networkResponse.data), 2);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("fullname", fullname);
                        params.put("dob", dob);
                        params.put("email", mail);
                        params.put("country_id", country);
                        params.put("jop_title_id", job);
                        params.put("gender", gander);
                        params.put("step", "2");
                        params.put("token", AppController.getInstance().UserKey());
                        return params;
                    }
                };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    protected void UploadImage(File profilePic) {
        new ProfileRepo().uploadProfilePic(AppController.getInstance().UserKey(), profilePic)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        Log.d("RegPresenterDone", "Done");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("RegPresenterErr", e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.d("RegPresenterOnNext", responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    protected Observable<String> GetCountries() {

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                StringRequest stringRequest =
                        new StringRequest(Request.Method.GET, URLs.Countries,
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

    protected Observable<String> GetJobs() {

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                StringRequest stringRequest =
                        new StringRequest(Request.Method.GET, URLs.Jobs,
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

    public interface VolleyCallback {
        void onSuccess(String result, int step) throws JSONException;
    }
}
