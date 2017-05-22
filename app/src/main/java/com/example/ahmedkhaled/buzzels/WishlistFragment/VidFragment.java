package com.example.ahmedkhaled.buzzels.WishlistFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ahmedkhaled.buzzels.Material.MaterialObject;
import com.example.ahmedkhaled.buzzels.R;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by seif on 3/19/2017.
 */
public class VidFragment extends android.support.v4.app.Fragment implements WishListView {

    private LinearLayoutManager manager;
    private RecyclerView recyclerView;
    private WishListPresenter presenter;
    private CustAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view;
        view = inflater.inflate(R.layout.activity_video_pic, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_wish);
        presenter = new WishListPresenter(this, new WishListModel());
        presenter.GetWIsh();

        return view;
    }

    @Override
    public void PutData(List<MaterialObject> data) {
        adapter = new CustAdapter(getActivity(), data, presenter);
        manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void NoWishlist() {

    }
}
