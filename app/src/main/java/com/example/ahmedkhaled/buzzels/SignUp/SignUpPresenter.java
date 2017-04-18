package com.example.ahmedkhaled.buzzels.SignUp;

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import com.example.ahmedkhaled.buzzels.Utils.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.button;
import static com.android.volley.VolleyLog.TAG;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public class SignUpPresenter implements SignUpModel.VolleyCallback {
    private SignUpView view;
    private SignUpModel model;
    private String username, password, cp, fullname, dob, mail, gender;

    protected SignUpPresenter(SignUpView view, SignUpModel model) {
        this.view = view;
        this.model = model;
    }

    protected void StepOne() {
        username = view.getUsername();
        fullname = view.getFullname();
        dob = view.getDate();
        mail = view.getEmail();
        boolean flag = CheckMail(mail);
        if (!flag) {
            view.ErrorMessage("Invalid email");
            return;
        }
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

        if (gender.equals("Male")) {
            gender = "0";
        } else {
            gender = "1";
        }
        if (password.equals(cp) && !password.isEmpty()) {
            String pass = HashPassword(password);
            model.StepOne(this, username, pass);
        } else {
            view.errorinpasword();
            return;
        }
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
        model.StepTwo(this, fullname, dob, mail, "2", "1", gender);
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

    /*public void GetImage() {
        Intent intent = new Intent();
        intent.setType("image*//*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String[] proj = {MediaStore.Images.Media.DATA};
            String result = null;
            CursorLoader cursorLoader = new CursorLoader(
                    this,
                    data.getData(), proj, null, null, null);
            Cursor cursor = cursorLoader.loadInBackground();

            if (cursor != null) {
                int column_index =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                result = cursor.getString(column_index);
                Log.d("ahmeddd", result);
                button.setImageURI(data.getData());
                PhotoURL = result;

            }
        }
     }*/
}
