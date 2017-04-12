package com.example.ahmedkhaled.buzzels.WishlistFragment;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ahmedkhaled.buzzels.R;
import com.example.ahmedkhaled.buzzels.Utils.AppController;
import com.example.ahmedkhaled.buzzels.Utils.URLs;

import java.util.HashMap;
import java.util.Map;

public class WishlistActivity extends FragmentActivity implements ActionBar.TabListener {
    private ViewPager viewPager;
    private ActionBar actionBar;
    private CustFragmentPagerAdapter custFragmentPagerAdapter;
    private String[] category = {"Videos", "Pictures", "Sound"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.wishlist_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        custFragmentPagerAdapter = new CustFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(custFragmentPagerAdapter);

        /*
         * Get Action Aar and make mode tabs
         */
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(false);

        /*
         * Set data on Action Bar
         */
        for (int i = 0; i < category.length; i++) {
            actionBar.addTab(actionBar.newTab().setText(category[i]).setTabListener(this));
        }

        /*
         * OnClick listener on ViewPager
         */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        GetWish();
    }

    private void GetWish() {
        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, URLs.User_WishList,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("ahmeddd", response);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("ahmeddd", error.toString());
                            }

                        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    /*
     * Implement actionBar.setTabListener
     */
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
