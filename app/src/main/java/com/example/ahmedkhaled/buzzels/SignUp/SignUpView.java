package com.example.ahmedkhaled.buzzels.SignUp;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public interface SignUpView {

    String getUsername();

    String getFullname();

    String getEmail();

    String getPassword();

    String getCpassword();

    String getGender();

    String getDate();

    String getCountry();

    String getJob();

    void ErrorMessage(String s);

    void errorinpasword();

    void enterdata();

    void Startactivity();

}
