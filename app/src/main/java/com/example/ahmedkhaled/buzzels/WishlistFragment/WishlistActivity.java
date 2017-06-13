package com.example.ahmedkhaled.buzzels.WishlistFragment;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.Toolbar;

import com.example.ahmedkhaled.buzzels.R;

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
        actionBar.setTitle("");
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.wishlist_logo);
        actionBar.setStackedBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#311b92")));
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
