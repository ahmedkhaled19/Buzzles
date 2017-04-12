package com.example.ahmedkhaled.buzzels.Collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.ahmedkhaled.buzzels.Category.MOPobject;
import com.example.ahmedkhaled.buzzels.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AhmedKhaled on 3/29/2017.
 */

public class CollectionAdaptor extends BaseAdapter {

    List<MOPobject> data = new ArrayList<>();
    Context context;

    public CollectionAdaptor(List<MOPobject> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.image_grid);
        Picasso.with(context).load(data.get(position).getImageURL()).into(imageView);
        return view;
    }
}
