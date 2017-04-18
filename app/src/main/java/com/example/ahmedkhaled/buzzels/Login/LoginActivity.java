package com.example.ahmedkhaled.buzzels.Login;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ahmedkhaled.buzzels.MainActivity;
import com.example.ahmedkhaled.buzzels.R;
import com.example.ahmedkhaled.buzzels.SignUp.SignUpActivity;
import com.example.ahmedkhaled.buzzels.Utils.AppController;
import com.example.ahmedkhaled.buzzels.Utils.Constants;
import com.example.ahmedkhaled.buzzels.Utils.URLs;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements LoginView {
    TextView regTxt;
    EditText username;
    EditText password;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        regTxt = (TextView) findViewById(R.id.reg_txt);
        username = (EditText) findViewById(R.id.log_email);
        password = (EditText) findViewById(R.id.log_pw);
        presenter = new LoginPresenter(this, new LoginModel());
        presenter.CheckLoged();
        regTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register();
            }
        });
    }

    public void Login(View view) {
        presenter.Login();
    }


    @Override
    public String Username() {
        return username.getText().toString().trim();
    }

    @Override
    public String Password() {
        return password.getText().toString().trim();
    }

    @Override
    public void ErrorMassage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void StartRegister() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    @Override
    public void StartMain() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finishAffinity();
        System.exit(0);
        super.onBackPressed();
    }


}
