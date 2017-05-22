package com.example.ahmedkhaled.buzzels.Material;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedkhaled.buzzels.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by AhmedKhaled on 3/21/2017.
 */

public class CustRecyclerAdapter extends RecyclerView.Adapter<CustRecyclerAdapter.Holder> {

    private Context context;
    private List<MaterialObject> material_list;
    private MaterialPresenter presenter;

    public CustRecyclerAdapter(Context context, List<MaterialObject> material_list, MaterialPresenter presenter) {
        this.context = context;
        this.material_list = material_list;
        this.presenter = presenter;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_item, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final MaterialObject obj = material_list.get(position);

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
                try {
                    String shareBody = material_list.get(position).getName() + " is created By \n";
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String sAux = "http://version2buzzles.itsgd.org/ \n";
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody + sAux);
                    sharingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(sharingIntent);
                } catch (Exception e) {
                    Toast.makeText(context, "Error while sharing", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (material_list.get(position).is_wishlisted()) {
                    material_list.get(position).set_wishlisted(false);
                    presenter.Unwish(material_list.get(position).getId());
                    holder.wish.setImageResource(R.drawable.unwish);
                    Toast.makeText(context, "UnWish", Toast.LENGTH_SHORT).show();
                } else {
                    material_list.get(position).set_wishlisted(true);
                    holder.wish.setImageResource(R.drawable.wish);
                    presenter.Wish(material_list.get(position).getId());
                    Toast.makeText(context, "Wish", Toast.LENGTH_SHORT).show();
                }

            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (material_list.get(position).is_liked()) {
                    holder.like.setImageResource(R.drawable.unlike);
                    material_list.get(position).set_liked(false);
                    presenter.Unlike(material_list.get(position).getId());
                    Toast.makeText(context, "Unlike", Toast.LENGTH_SHORT).show();

                } else {
                    holder.like.setImageResource(R.drawable.liked);
                    material_list.get(position).set_liked(true);
                    presenter.Like(material_list.get(position).getId());
                    Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show();

                }
            }
        });
        Picasso.with(context).

                load(obj.getImg_url()).

                into(holder.item_image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                build_dialoge(obj.getImg_url());
            }
        });
    }

    @Override
    public int getItemCount() {
        return material_list.size();
    }


    private void build_dialoge(String image_url) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_material_item, null);
        builder.setView(dialogView);
        final ImageView imageView = (ImageView) dialogView.findViewById(R.id.image_dialog);
        Picasso.with(context).load(image_url).into(imageView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public class Holder extends RecyclerView.ViewHolder {
        ImageView item_image, like, share, wish;
        TextView title;
        CardView cardView;

        public Holder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_main);
            item_image = (ImageView) itemView.findViewById(R.id.item_img);
            like = (ImageView) itemView.findViewById(R.id.like_icon);
            share = (ImageView) itemView.findViewById(R.id.share_icon);
            wish = (ImageView) itemView.findViewById(R.id.wishlist_icon);
            title = (TextView) itemView.findViewById(R.id.item_title);
        }
    }
}
