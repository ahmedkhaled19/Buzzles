package com.example.ahmedkhaled.buzzels.WishlistFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmedkhaled.buzzels.R;

/**
 * Created by seif on 3/19/2017.
 */
public class SoundFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view;
        view = inflater.inflate(R.layout.activity_sound, container, false);
        return view;
    }
}
