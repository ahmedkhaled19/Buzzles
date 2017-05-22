package com.example.ahmedkhaled.buzzels.Collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ahmedkhaled.buzzels.Category.MOPobject;
import com.example.ahmedkhaled.buzzels.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AhmedKhaled on 3/29/2017.
 */

public class CollectionAdaptor extends RecyclerView.Adapter<CollectionAdaptor.Holder> {

    private List<MOPobject> data = new ArrayList<>();
    private Context context;
    private CollectionPresenter presenter;

    public CollectionAdaptor(List<MOPobject> data, Context context, CollectionPresenter presenter) {
        this.data = data;
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.collection_item, parent, false);
        return new Holder(itemView);
    }


    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        String image = data.get(position).getImageURL();
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
                presenter.onclick(position);
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
            imageView = (ImageView) itemView.findViewById(R.id.image_grid);
            progressBar = (ProgressBar) itemView.findViewById(R.id.collection_progress);

        }
    }

}
