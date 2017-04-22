package com.example.ahmedkhaled.buzzels.SignUp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.example.ahmedkhaled.buzzels.Utils.AppController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public class SignUpPresenter implements SignUpModel.VolleyCallback {
    private SignUpView view;
    private SignUpModel model;
    private String username, password, cp, fullname, dob, mail, gender, CountryId, JopID;
    private File filepath = null;
    private List<GJ> Country, Job;
    private ArrayList<String> CD = new ArrayList<>();
    private ArrayList<String> JD = new ArrayList<>();
    private Observable datastream;

    protected SignUpPresenter(SignUpView view, SignUpModel model) {
        this.view = view;
        this.model = model;
        datastream = model.GetCountries();
        GetCountry();
        datastream = model.GetJobs();
        GetJob();
    }

    protected void StepOne() {
        username = view.getUsername();
        fullname = view.getFullname();
        dob = view.getDate();
        mail = view.getEmail();
        CountryId = view.getCountry();
        JopID = view.getJob();
        JopID = Job.get(Integer.parseInt(JopID)).getId();
        gender = view.getGender();
        password = view.getPassword();
        cp = view.getCpassword();
        if (gender == null) {
            view.enterdata();
            return;
        }
        if (username.isEmpty() || fullname.isEmpty() || dob.isEmpty() || mail.isEmpty()) {
            view.enterdata();
            return;
        }
        boolean flag = CheckMail(mail);
        if (!flag) {
            view.ErrorMessage("Invalid email");
            return;
        }
        if (gender.equals("Male")) {
            gender = "0";
        } else {
            gender = "1";
        }
        boolean F = CheckPass(password);
        if (!F) {
            view.ErrorMessage("Invalid Password");
            return;
        }
        if (password.equals(cp) && !password.isEmpty()) {
            String pass = HashPassword(password);
            model.StepOne(this, username, pass);
        } else {
            view.errorinpasword();
            return;
        }
    }

    private boolean CheckPass(String password) {
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN =
                "((?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean CheckMail(String mail) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = mail;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;

    }

    protected void StepTwo() {
        model.StepTwo(this, fullname, dob, mail, CountryId, JopID, gender);
    }


    private String HashPassword(final String s) {
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

    @Override
    public void onSuccess(String result, int step) throws JSONException {
        if (step == 1) {
            JSONObject res = new JSONObject(result);
            String statue = res.getString("status");
            if (statue.equals("false")) {
                String message = res.getString("message");
                view.ErrorMessage(message);
            } else {
                String key = res.getString("key");
                AppController.getInstance().register1(key);
                if (filepath != null) {
                    model.UploadImage(filepath);
                }
                StepTwo();

            }
        } else if (step == 2) {
            JSONObject res = new JSONObject(result);
            String statue = res.getString("status");
            if (statue.equals("false")) {
                String message = res.getString("message");
                view.ErrorMessage(message);
            } else {
                String key = res.getString("token");
                AppController.getInstance().register2(key);
                AppController.getInstance().loginUser();
                view.Startactivity();
            }

        }
    }

    protected void GetCountry() {

        datastream.observeOn(Schedulers.io())
                .map(new Function<String, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(String query) throws JSONException {
                        JSONArray res = new JSONArray(query);
                        GsonBuilder builder = new GsonBuilder();
                        Gson mGson = builder.create();
                        Country = Arrays.asList(mGson.fromJson(String.valueOf(res), GJ[].class));
                        for (int i = 0; i < Country.size(); i++) {
                            CD.add(Country.get(i).getCountryName());
                        }
                        return CD;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<String>>() {
                    @Override
                    public void accept(ArrayList<String> data) {
                        view.SetCountry(data);
                    }
                });
    }

    protected void GetJob() {

        datastream.observeOn(Schedulers.io())
                .map(new Function<String, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(String query) throws JSONException {
                        JSONArray res = new JSONArray(query);
                        GsonBuilder builder = new GsonBuilder();
                        Gson mGson = builder.create();
                        Job = Arrays.asList(mGson.fromJson(String.valueOf(res), GJ[].class));
                        for (int i = 0; i < Job.size(); i++) {
                            JD.add((String) Job.get(i).getTitle());
                        }
                        return JD;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<String>>() {
                    @Override
                    public void accept(ArrayList<String> data) {
                        view.SetJob(data);
                    }
                });

    }

    public void GetImage(String path) {
        if (path != null) {
            filepath = new File(path);
        }
    }
}