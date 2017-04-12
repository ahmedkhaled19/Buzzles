package com.example.ahmedkhaled.buzzels.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedkhaled.buzzels.Login.LoginActivity;
import com.example.ahmedkhaled.buzzels.R;
import com.example.ahmedkhaled.buzzels.WishlistFragment.WishlistActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by AhmedKhaled on 2/22/2017.
 */

public class Profile extends Fragment implements ProfileView {

    CircleImageView imageView;
    TextView Name, Job;
    ProfilePresenter presenter;
    Button analysis, wishlist, logout;
    ProgressBar progressBar_profile;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        imageView = (CircleImageView) view.findViewById(R.id.profileimage);
        Name = (TextView) view.findViewById(R.id.profile_name);
        Job = (TextView) view.findViewById(R.id.profile_type);
        analysis = (Button) view.findViewById(R.id.analysis);
        wishlist = (Button) view.findViewById(R.id.wishlist);
        logout = (Button) view.findViewById(R.id.logout);
        progressBar_profile =(ProgressBar)view.findViewById(R.id.the_progress_profile);
        presenter = new ProfilePresenter(this, new ProfileModel());
        presenter.GetData();
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.GoToWishlist();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.LogOut();
            }
        });
        return view;
    }


    @Override
    public void Name(String name) {
        Name.setText(name);
    }

    @Override
    public void Job(String job) {
        Job.setText(job);
    }

    @Override
    public void Profile(String url) {
        Picasso.with(getContext()).load(url).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressBar_profile.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {
                Toast.makeText(getActivity(), "Error in loading photo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void StartAnalysis() {

    }

    @Override
    public void StartWishlist() {
        startActivity(new Intent(getActivity(), WishlistActivity.class));
    }

    @Override
    public void LogOut() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }


}
