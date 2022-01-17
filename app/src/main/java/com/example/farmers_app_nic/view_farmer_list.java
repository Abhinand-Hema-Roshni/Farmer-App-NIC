package com.example.farmers_app_nic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class view_farmer_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_farmer_list);

        Button select_btn;
        select_btn = findViewById(R.id.select_btn);

        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view_farmer_list.this, view_crop_details.class);
                startActivity(intent);
            }
        });
    }
}