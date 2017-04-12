package com.example.ahmedkhaled.buzzels.Category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ahmedkhaled.buzzels.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by AhmedKhaled on 2/22/2017.
 */

public class OpenProject extends Fragment implements MOPView {

    private LinearLayout dotsLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private TextView[] dots;
    private MOPPresenter presenter;
    private RecyclerAdaptor adaptor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.openproject, container, false);

        dotsLayout = (LinearLayout) view.findViewById(R.id.layout1);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_OP);
        if (isAdded()) {
            presenter = new MOPPresenter(new MOPModel(), this, "2");
            presenter.GetMaterial();
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dx > 0) {
                    int pastVisiblesItems = manager.findFirstVisibleItemPosition();
                    presenter.SetDots(pastVisiblesItems, adaptor.getItemCount());
                }
            }
        });

        return view;
    }

    @Override
    public void setupDots(int page, int size) {
        if (isAdded()) {
            dots = new TextView[size];
            dotsLayout.removeAllViews();
            int active = getActivity().getResources().getColor(R.color.tempo);
            int inactive = getActivity().getResources().getColor(R.color.not_active);
            for (int i = 0; i < dots.length; i++) {
                dots[i] = new TextView(getContext());
                dots[i].setText(Html.fromHtml("&#8226;"));
                dots[i].setTextSize(35);
                dots[i].setTextColor(inactive);
                dotsLayout.addView(dots[i]);
            }
            if (dots.length > 0)
                dots[page].setTextColor(active);
        }
    }

    @Override
    public void setData(List<MOPobject> data) {
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        adaptor = new RecyclerAdaptor(data, getActivity());
        recyclerView.setAdapter(adaptor);
    }

}
