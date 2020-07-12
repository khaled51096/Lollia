package com.example.lollia.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lollia.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public  class ShowDataViewHolder extends RecyclerView.ViewHolder{
    private final TextView image_titlee;
    private final ImageView image_url;

    public ShowDataViewHolder(@NonNull View itemView) {
        super(itemView);
        image_titlee = (TextView)itemView.findViewById(R.id.image_title);
        image_url = (ImageView)itemView.findViewById(R.id.fetch_image);
    }

    public void Image_title(String title){
        image_titlee.setText(title);
    }

    public void Image_url( String title)
    {
        Glide.with(itemView.getContext())
                .load(title)
                .placeholder(R.drawable.user)
                .into(image_url);

    }
}

