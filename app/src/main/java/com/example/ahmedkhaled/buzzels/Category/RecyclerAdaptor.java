package com.example.ahmedkhaled.buzzels.Category;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ahmedkhaled.buzzels.MainActivity;
import com.example.ahmedkhaled.buzzels.Material.MaterialActivity;
import com.example.ahmedkhaled.buzzels.R;
import com.example.ahmedkhaled.buzzels.SubCategory.SubCategory;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AhmedKhaled on 3/18/2017.
 */

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.Holder> {

    List<MOPobject> photo;
    Context context;

    public RecyclerAdaptor(List<MOPobject> photo, Context context) {
        this.photo = photo;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new Holder(itemView);
    }


    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        String image = photo.get(position).getImageURL();
        Picasso.with(context).load(image).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {
                Toast.makeText(context, "error in download", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = photo.get(position).getType();
                switch (x) {
                    case 1:
                        context.startActivity(new Intent(context, SubCategory.class)
                                .putExtra("id", photo.get(position).getId()));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, MaterialActivity.class)
                                .putExtra("id", photo.get(position).getId())
                                .putExtra("from",1));
                        break;
                    default:
                        Toast.makeText(context, "Click right on the item", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return photo.size();
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

