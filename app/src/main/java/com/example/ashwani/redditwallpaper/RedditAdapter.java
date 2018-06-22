package com.example.ashwani.redditwallpaper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ashwani.redditwallpaper.Data.Child;
import com.example.ashwani.redditwallpaper.Data.Data_;

import java.util.ArrayList;
import java.util.List;

public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.ViewHolder>{
    private List<Child> childrenList=new ArrayList<>();
    Context context=null;

    public RedditAdapter(List<Child> childrenList) {

        this.childrenList = childrenList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View itemView= LayoutInflater.from(context).inflate(R.layout.post_single_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Data_ data_ = childrenList.get(position).getData();
        Glide.with(context).load(data_.getThumbnail()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ImageActivity.class);
                intent.putExtra("url",data_.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return childrenList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.post_item_image);
            cardView=itemView.findViewById(R.id.card_view_gallery_image);
        }
    }
}
