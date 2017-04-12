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
import java.util.List;

/**
 * Created by AhmedKhaled on 2/22/2017.
 */

public class Material extends Fragment implements MOPView {

    private LinearLayout dotsLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private TextView[] dots;
    private MOPPresenter presenter;
    private RecyclerAdaptor adaptor;
    private int position = -1;
    private String SELECTED_KEY = "position";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.material, container, false);
        dotsLayout = (LinearLayout) view.findViewById(R.id.nader);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_material);

        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            position = savedInstanceState.getInt(SELECTED_KEY);
        }

        if (isAdded()) {
            presenter = new MOPPresenter(new MOPModel(), this, "1");
            presenter.GetMaterial();
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dx > 0) {
                    int pastVisiblesItems = manager.findFirstVisibleItemPosition();
                    presenter.SetDots(pastVisiblesItems, adaptor.getItemCount());
                    position = pastVisiblesItems;
                }
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (position != -1) {
            outState.putInt(SELECTED_KEY, position);
        }
        super.onSaveInstanceState(outState);
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
        adaptor = new RecyclerAdaptor(data, getActivity());
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adaptor);
        if (position != -1) {
            recyclerView.smoothScrollToPosition(position);
        }
    }
}
