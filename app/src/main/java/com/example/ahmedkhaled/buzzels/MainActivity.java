package com.example.ahmedkhaled.buzzels;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ahmedkhaled.buzzels.Category.Material;
import com.example.ahmedkhaled.buzzels.Category.OpenProject;
import com.example.ahmedkhaled.buzzels.Category.Products;
import com.example.ahmedkhaled.buzzels.Profile.Profile;
import com.example.ahmedkhaled.buzzels.Collection.Collection;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction transaction;
    private int active_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState != null) {
            active_position = savedInstanceState.getInt("position");
        }
        BottomNavigation bottomNavigation = (BottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setDefaultItem(active_position);
        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int itemId) {
                switch (itemId) {
                    case R.id.tab_matrial:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.mainframe, new Material());
                        active_position = 0;
                        break;
                    case R.id.tab_openproject:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.mainframe, new OpenProject());
                        active_position = 1;
                        break;
                    case R.id.tab_product:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.mainframe, new Products());
                        active_position = 2;
                        break;
                    case R.id.tab_collection:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.mainframe, new Collection());
                        active_position = 3;
                        break;
                    case R.id.tab_profile:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.mainframe, new Profile());
                        active_position = 4;
                        break;
                }
                transaction.commit();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt("position", active_position);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        active_position = savedInstanceState.getInt("position");
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
