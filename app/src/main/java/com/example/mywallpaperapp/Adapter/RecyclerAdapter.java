package com.example.mywallpaperapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mywallpaperapp.FetchedModelClasses.ImageModel;
import com.example.mywallpaperapp.R;
import com.example.mywallpaperapp.SetActivity;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DataViewholders> {
    Context context;
    ArrayList<ImageModel> wallpaperlist;

    public RecyclerAdapter(Context context, ArrayList<ImageModel> image_lists) {
        this.context = context;
        this.wallpaperlist = image_lists;
    }

    @NonNull
    @Override
    public RecyclerAdapter.DataViewholders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_views, null, false);

        return new DataViewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.DataViewholders holder, int position) {

        holder.photos_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.photos_title.setMovementMethod(LinkMovementMethod.getInstance());
                holder.photos_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                        browserIntent.setData(Uri.parse("https://www.pexels.com/"));
                        browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(browserIntent);
                    }
                });
            }
        });

        Glide.with(context).load(wallpaperlist.get(position).getSrc().getPortrait()).into(holder.photo_items);
        holder.photo_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SetActivity.class);
                intent.putExtra("product_images", wallpaperlist.get( holder.getAdapterPosition()).getSrc().getPortrait());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wallpaperlist.size();
    }

    public class DataViewholders extends RecyclerView.ViewHolder {

        ImageView photo_items;
        TextView photos_title;

        public DataViewholders(@NonNull View itemView) {
            super(itemView);
            photo_items = itemView.findViewById(R.id.images_s);
            photos_title = itemView.findViewById(R.id.image_title);
        }
    }
}
