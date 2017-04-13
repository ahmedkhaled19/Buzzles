package com.example.ahmedkhaled.buzzels.Material;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ahmedkhaled.buzzels.R;

import java.util.ArrayList;
import java.util.List;

public class MaterialActivity extends AppCompatActivity implements MaterialView {

    private CustRecyclerAdapter adapter;
    private LinearLayoutManager manager;
    private RecyclerView recyclerView;
    private MaterialPresenter presenter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        recyclerView = (RecyclerView) findViewById(R.id.matrial_view);
        intent = getIntent();
        presenter = new MaterialPresenter(this, new MaterialModel(), intent.getStringExtra("id"), intent.getIntExtra("from", 0));
        presenter.GetData();
    }

    @Override
    public void putdata(List<MaterialObject> data) {
        adapter = new CustRecyclerAdapter(getApplicationContext(), data, presenter);
        manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
