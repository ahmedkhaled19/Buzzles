package com.example.ahmedkhaled.buzzels.WishlistFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by seif on 3/19/2017.
 */
public class CustFragmentPagerAdapter extends FragmentPagerAdapter {

    public CustFragmentPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new VidFragment();
        if(position == 1)
            return new PicFragment();
        if(position == 2)
            return new SoundFragment();

        return null ;
    }


    @Override
    public int getCount() {
        return 3;
    }
}
