package com.example.farmers_app_nic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class add_crop_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_crop_details);

        FloatingActionButton add_crop_submit_button;
        add_crop_submit_button = findViewById(R.id.add_crop_submit_btn);
        add_crop_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), VolunteerHomePage.class);
                startActivity(intent);
            }
        });
    }
}