package com.example.lollia.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lollia.R;
import com.example.lollia.ui.home.MainActivity;
import com.example.lollia.ui.menu.pin_shop;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class Products extends Fragment {


    private ImageView firstp;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.products, container, false);
       firstp = (ImageView)view.findViewById(R.id.first_p);
        firstp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), Products_view.class);
                startActivity(intent);
            }
        });
        return view;

    }
}
