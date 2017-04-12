package com.example.ahmedkhaled.buzzels.Collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ahmedkhaled.buzzels.Category.MOPobject;
import com.example.ahmedkhaled.buzzels.MainActivity;
import com.example.ahmedkhaled.buzzels.Material.MaterialActivity;
import com.example.ahmedkhaled.buzzels.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by AhmedKhaled on 2/22/2017.
 */

public class Collection extends Fragment implements CollectionView {

    private CollectionPresenter presenter;
    private GridView gridView;
    private CollectionAdaptor adaptor;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collection, container, false);
        presenter = new CollectionPresenter(this, new CollectionModel());
        presenter.GetCollection();
        gridView = (GridView) view.findViewById(R.id.collection_grid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                presenter.onclick(position);
            }
        });
        return view;
    }

    @Override
    public void SetData(List<MOPobject> data) {
        adaptor = new CollectionAdaptor(data, getActivity());
        gridView.setAdapter(adaptor);
    }

    @Override
    public void Start(String id) {
        startActivity(new Intent(getActivity(), MaterialActivity.class)
                .putExtra("id", id)
                .putExtra("from", 3));
    }
}
