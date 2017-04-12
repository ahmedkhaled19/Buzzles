package com.example.ahmedkhaled.buzzels.Login;

import com.example.ahmedkhaled.buzzels.Category.MOPobject;
import com.example.ahmedkhaled.buzzels.Utils.AppController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public class LoginPresenter implements LoginModel.VolleyCallback {

    LoginView view;
    LoginModel model;
    String username, password;

    public LoginPresenter(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
    }

    protected void Login() {
        username = view.Username();
        password = view.Password();
        if (username.isEmpty() || password.isEmpty()) {
            view.ErrorMassage("please enter UserName and Password");
            return;
        }
        password = HashPassword(password);
        model.LogIN(this, username, password);

    }

    protected void CheckLoged() {
        Observable.just(false)
                .observeOn(Schedulers.computation())
                .map(new Function<Boolean, Boolean>() {
                    @Override
                    public Boolean apply(Boolean flag) {
                        return AppController.getInstance().isLoggedIn();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean flag) throws Exception {
                        if (flag) {
                            view.StartMain();
                        }
                    }
                });
    }

    @Override
    public void onSuccess(String result) throws JSONException {
        JSONObject res = new JSONObject(result);
        String statue = res.getString("status");
        if (statue.equals("false")) {
            String message = res.getString("message");
            view.ErrorMassage(message);
        } else {
            String key = res.getString("key");
            String token = res.getString("token");
            AppController.getInstance().register1(key);
            AppController.getInstance().register2(token);
            AppController.getInstance().loginUser();
            view.StartMain();
        }
    }

    public String HashPassword(final String s) {
        final String MD5 = "MD5";
        try {
            //Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            //Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2) h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "error in hash";
    }

    public void register() {
        view.StartRegister();
    }
}
