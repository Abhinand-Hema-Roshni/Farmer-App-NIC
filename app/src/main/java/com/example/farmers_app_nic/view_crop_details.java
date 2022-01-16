package com.example.farmers_app_nic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class view_crop_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_crop_details);
        FloatingActionButton add_crop_btn;
        add_crop_btn = findViewById(R.id.add_crop_btn);
        add_crop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), add_crop_details.class);
                startActivity(intent);
            }
        });
    }

}