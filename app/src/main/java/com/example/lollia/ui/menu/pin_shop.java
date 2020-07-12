package com.example.lollia.ui.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lollia.R;
import com.example.lollia.ui.fragment.Home;
import com.example.lollia.ui.fragment.Products;
import com.example.lollia.ui.home.MainActivity;

public class pin_shop extends AppCompatActivity {
    private Button shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_shop);
        shop = (Button)findViewById(R.id.back_to_product);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(pin_shop.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
