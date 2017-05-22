package com.example.ahmedkhaled.buzzels.SubCategory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ahmedkhaled.buzzels.R;

import java.util.ArrayList;
import java.util.List;

public class SubCategory extends AppCompatActivity implements SubCategoryView {

    private LinearLayout dotsLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private TextView[] dots;
    private SubCategoryPresenter presenter;
    private RecyclerAdaptor adaptor;
    private Intent intent;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_category_activity);
        dotsLayout = (LinearLayout) findViewById(R.id.layout4);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_subcategory);
        intent = getIntent();
        textView = (TextView) findViewById(R.id.hit_text_sub);
        textView.setVisibility(View.INVISIBLE);
        presenter = new SubCategoryPresenter(new SubCategoryModel(), this);
        presenter.GetData(intent.getStringExtra("id"));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dx > 0) {
                    int pastVisiblesItems = manager.findFirstVisibleItemPosition();
                    presenter.SetDots(pastVisiblesItems, adaptor.getItemCount());
                }
            }
        });
    }

    @Override
    public void SetData(List<SubObject> data) {
        if (!data.isEmpty()) {
            textView.setVisibility(View.INVISIBLE);
            adaptor = new RecyclerAdaptor(data, getApplicationContext());
            manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adaptor);
        }else {
            textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void SetDots(int size, int page) {
        dots = new TextView[size];
        dotsLayout.removeAllViews();
        int active = getApplicationContext().getResources().getColor(R.color.tempo);
        int inactive = getApplicationContext().getResources().getColor(R.color.not_active);
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getApplicationContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(inactive);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[page].setTextColor(active);
    }

}
