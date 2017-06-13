package com.example.ahmedkhaled.buzzels.WishlistFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
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
                try {
                    String shareBody = WishData.get(position).getName() + " is created By \n";
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
                if (WishData.get(position).is_wishlisted()) {
                    presenter.Unwish(WishData.get(position).getId());
                    WishData.remove(obj);
                    notifyDataSetChanged();
                    holder.wish.setImageResource(R.drawable.unwish);
                    Toast.makeText(context, "Remove from WishList", Toast.LENGTH_SHORT).show();
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

    @Override
    public int getItemCount() {
        return WishData.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView item_image, share, wish;
        TextView title;
        CardView cardView;
        public Holder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_wish);
            item_image = (ImageView) itemView.findViewById(R.id.wish_item_img);
            share = (ImageView) itemView.findViewById(R.id.wish_share_icon);
            wish = (ImageView) itemView.findViewById(R.id.wish_wishlist_icon);
            title = (TextView) itemView.findViewById(R.id.wish_item_title);
        }
    }
}
