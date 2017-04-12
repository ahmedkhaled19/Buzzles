package com.example.ahmedkhaled.buzzels.Login;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public interface LoginView {

    String Username();

    String Password();

    void ErrorMassage(String s);

    void StartRegister();

    void StartMain();
}
