package com.example.ahmedkhaled.buzzels.WishlistFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedkhaled.buzzels.Material.MaterialObject;
import com.example.ahmedkhaled.buzzels.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import static android.R.attr.bitmap;
import static com.example.ahmedkhaled.buzzels.R.drawable.share;

/**
 * Created by seif on 3/19/2017.
 */
public class CustAdapter extends RecyclerView.Adapter<CustAdapter.Holder> {

    private Context context;
    private List<MaterialObject> WishData;
    private WishListPresenter presenter;

    public CustAdapter(Context context, List<MaterialObject> WishData, WishListPresenter presenter) {
        this.context = context;
        this.WishData = WishData;
        this.presenter = presenter;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vid_pic_list_item, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final MaterialObject obj = WishData.get(position);

        holder.title.setText(obj.getName());

        if (obj.is_wishlisted()) {
            holder.wish.setImageResource(R.drawable.wish);
        } else {
            holder.wish.setImageResource(R.drawable.unwish);
        }

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareBody = WishData.get(position).getName() + " is created By Buzzles.org";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, WishData.get(position).getName() + shareBody);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                sharingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sharingIntent.setType("image/*");
                Uri bmpUri = presenter.getLocalBitmapUri(holder.item_image,context);
                sharingIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                context.startActivity(sharingIntent);
            }
        });

        holder.wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (WishData.get(position).is_wishlisted()) {
                    presenter.Unwish(WishData.get(position).getId());
                    WishData.remove(obj);
                    notifyDataSetChanged();
                    holder.wish.setImageResource(R.drawable.unwish);
                    Toast.makeText(context, "UnWish", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Picasso.with(context).

                load(obj.getImg_url()).

                into(holder.item_image);
    }

    @Override
    public int getItemCount() {
        return WishData.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView item_image, share, wish;
        TextView title;

        public Holder(View itemView) {
            super(itemView);
            item_image = (ImageView) itemView.findViewById(R.id.wish_item_img);
            share = (ImageView) itemView.findViewById(R.id.wish_share_icon);
            wish = (ImageView) itemView.findViewById(R.id.wish_wishlist_icon);
            title = (TextView) itemView.findViewById(R.id.wish_item_title);
        }
    }
}
