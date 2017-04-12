package com.example.ahmedkhaled.buzzels.Material;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedkhaled.buzzels.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public class CustRecyclerAdapter extends RecyclerView.Adapter<CustRecyclerAdapter.Holder> {
    private Context context;
    private ArrayList<material_object> material_list;

    public CustRecyclerAdapter(Context context, ArrayList<material_object> material_list) {
        this.context = context;
        this.material_list = material_list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_item, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        material_object obj = material_list.get(position);

        holder.price.setText(obj.getPrice());
        holder.title.setText(obj.getName());
        if (obj.is_liked()) {
            holder.like.setImageResource(R.drawable.liked);
        } else {
            holder.like.setImageResource(R.drawable.unlike);
        }
        if (obj.is_wishlisted()) {
            holder.wish.setImageResource(R.drawable.wish);
        } else {
            holder.wish.setImageResource(R.drawable.unwish);
        }
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "share", Toast.LENGTH_SHORT).show();
            }
        });
        holder.wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "wish", Toast.LENGTH_SHORT).show();

            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "like", Toast.LENGTH_SHORT).show();

            }
        });

        Picasso.with(context).load(obj.getImg_url()).into(holder.item_image);
    }

    @Override
    public int getItemCount() {
        return material_list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView item_image, like, share, wish;
        TextView title, price;

        public Holder(View itemView) {
            super(itemView);
            item_image = (ImageView) itemView.findViewById(R.id.item_img);
            like = (ImageView) itemView.findViewById(R.id.like_icon);
            share = (ImageView) itemView.findViewById(R.id.share_icon);
            wish = (ImageView) itemView.findViewById(R.id.wishlist_icon);
            title = (TextView) itemView.findViewById(R.id.item_title);
            price = (TextView) itemView.findViewById(R.id.item_price);
        }
    }
}
