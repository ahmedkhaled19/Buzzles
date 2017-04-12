package com.example.ahmedkhaled.buzzels.SubCategory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ahmedkhaled.buzzels.Material.MaterialActivity;
import com.example.ahmedkhaled.buzzels.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by AhmedKhaled on 3/29/2017.
 */

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.Holder> {

    List<SubObject> data;
    Context context;

    public RecyclerAdaptor(List<SubObject> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new Holder(itemView);

    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        String image = data.get(position).getImage();
        Picasso.with(context).load(image).into(holder.imageView, new Callback() {
            public void onSuccess() {
                holder.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, MaterialActivity.class)
                        .putExtra("id", data.get(position).getId())
                        .putExtra("from",2)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.recycler_image);
            progressBar = (ProgressBar) itemView.findViewById(R.id.the_progress);

        }
    }
}
