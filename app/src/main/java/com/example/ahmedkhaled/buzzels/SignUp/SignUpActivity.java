package com.example.ahmedkhaled.buzzels.SignUp;

import android.content.Intent;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.ahmedkhaled.buzzels.MainActivity;
import com.example.ahmedkhaled.buzzels.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SignUpActivity extends AppCompatActivity implements SignUpView {
    private CustomDatePicker calendar;
    private Spinner spinner;
    private EditText username, fullname, email, password, confirmpassword;
    private ImageView button;
    private RadioGroup ganeder;
    private RadioButton gselected;
    private SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        button = (ImageView) findViewById(R.id.reg_pic);
        username = (EditText) findViewById(R.id.reg_Uname);
        fullname = (EditText) findViewById(R.id.reg_Fname);
        email = (EditText) findViewById(R.id.reg_email);
        password = (EditText) findViewById(R.id.reg_pw);
        confirmpassword = (EditText) findViewById(R.id.reg_cpw);
        ganeder = (RadioGroup) findViewById(R.id.radio_choose);
        calendar = (CustomDatePicker) findViewById(R.id.calendar);
        calendar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        spinner = (Spinner) findViewById(R.id.joptitle);
        presenter = new SignUpPresenter(this, new SignUpModel());
    }


    public void SignUp(View view) {
        presenter.StepOne();
    }


    public void ChossePhoto(View view) {
        //presenter.GetImage();
    }

    @Override
    public String getUsername() {
        return username.getText().toString().trim();
    }

    @Override
    public String getFullname() {
        return fullname.getText().toString().trim();
    }

    @Override
    public String getEmail() {
        return email.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public String getCpassword() {
        return confirmpassword.getText().toString().trim();
    }

    @Override
    public String getGender() {
        int selected = ganeder.getCheckedRadioButtonId();
        gselected = (RadioButton) findViewById(selected);
        if (gselected == null) {
            return null;
        }
        return String.valueOf(gselected.getText());
    }

    @Override
    public String getDate() {
        int day = calendar.getDayOfMonth();
        int month = calendar.getMonth();
        int year = calendar.getYear();
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }

    @Override
    public String getCountry() {
        return null;
    }

    @Override
    public String getJob() {
        return null;
    }

    @Override
    public void ErrorMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void errorinpasword() {
        Toast.makeText(this, "password is empty or not match ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enterdata() {
        Toast.makeText(this, "Please fill all of the data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Startactivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}






