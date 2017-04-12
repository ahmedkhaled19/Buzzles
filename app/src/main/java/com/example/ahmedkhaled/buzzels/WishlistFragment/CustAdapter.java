package com.example.ahmedkhaled.buzzels.WishlistFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.ahmedkhaled.buzzels.R;

import java.util.ArrayList;

/**
 * Created by seif on 3/19/2017.
 */
public class CustAdapter extends ArrayAdapter{
    private ArrayList<Integer> data ;
    private Context context ;
    private ImageView imageView ;
    public CustAdapter(Context context , ArrayList<Integer> data){
        super(context , R.layout.vid_pic_list_item , R.id.img_item , data);
        this.context = context;
        this.data = data ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View newView = layoutInflater.inflate(R.layout.vid_pic_list_item ,null);

        imageView = (ImageView) newView.findViewById(R.id.img_item);
        imageView.setImageResource(data.get(position));

        return newView ;
    }
}
