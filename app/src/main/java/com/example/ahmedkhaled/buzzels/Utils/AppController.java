package com.example.ahmedkhaled.buzzels.Utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import static com.example.ahmedkhaled.buzzels.Utils.Constants.IS_LOGGED_IN;
import static com.example.ahmedkhaled.buzzels.Utils.Constants.SHARED_PREF_NAME;
import static com.example.ahmedkhaled.buzzels.Utils.Constants.User_Session;
import static com.example.ahmedkhaled.buzzels.Utils.Constants.User_Token;


public class AppController extends Application {

    private RequestQueue mRequestQueue;

    private static AppController mInstance;

    private SharedPreferences sharedPreferences;
    private ImageLoader mImageLoader;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        //calling the method to get the request queue and adding the requeust the the queuue
        getRequestQueue().add(req);
    }

    //method to cancle the pending requests
    public void cancelPendingRequests() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });
        }
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public void logout() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.apply();
    }

    public void register1(String token) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(User_Token, token);
        editor.apply();
    }

    public void register2(String session) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(User_Session, session);
        editor.apply();
    }

    public void loginUser() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.apply();
    }

    //This method will check whether the user is logged in or not
    public boolean isLoggedIn() {
        return getSharedPreferences().getBoolean(IS_LOGGED_IN, false);
    }

    public String UserKey() {
        return getSharedPreferences().getString(User_Token, null);
    }

    public String UserSession() {
        return getSharedPreferences().getString(User_Session, null);
    }


}
