package com.example.lollia.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lollia.R;

import androidx.fragment.app.Fragment;

public class Contact extends Fragment {
    private Button facebook;
    private Button instagram;
    private Button whatsapp;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.contact, container, false);

        facebook = (Button)view.findViewById(R.id.face_btn);
        instagram = (Button)view.findViewById(R.id.insta_btn);
        whatsapp = (Button)view.findViewById(R.id.whats_btn);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/Lollia2020/"));
                startActivity(i);

            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instagram.com/lolliafas/"));
                startActivity(i);
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://api.whatsapp.com/send?phone="+"0201029929220"));
                startActivity(i);

            }
        });
        return view;

    }
}
