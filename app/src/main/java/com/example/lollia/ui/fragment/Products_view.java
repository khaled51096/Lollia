package com.example.lollia.ui.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lollia.R;
import com.example.lollia.adapter.ShowDataViewHolder;
import com.example.lollia.data.show_data_items;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Products_view extends AppCompatActivity {
     private RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mref;
    private FirebaseRecyclerAdapter<show_data_items, ShowDataViewHolder> mfirebaseadapter;

    public Products_view(){
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_view);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mref = firebaseDatabase.getReference("product");

        recyclerView = (RecyclerView)findViewById(R.id.show_data_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(Products_view.this));
        Toast.makeText(Products_view.this,"wait",Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onStart(){
        super.onStart();

        mfirebaseadapter = new FirebaseRecyclerAdapter<show_data_items, ShowDataViewHolder>
                (show_data_items.class,R.layout.show_item,ShowDataViewHolder.class,mref)
        {

            @Override
            protected void populateViewHolder(ShowDataViewHolder viewholder, show_data_items model, int i) {
                viewholder.Image_title(model.getProduct_image_title());
                viewholder.Image_url(model.getProduct_image_url());

            }

            };

        recyclerView.setAdapter(mfirebaseadapter);


        }


           /* public void popularViewHolder(final ShowDataViewHolder viewholder, show_data_items model, final int position){
                viewholder.product_image_url(model.getProduct_image_url());
                viewholder.product_image_title(model.getProduct_image_title());

                viewholder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlartDialog.Builder builder = new AlertDialog.Builder(Products_view.this);

                    }
                });

            }*/




}
