package com.example.farmers_app_nic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VolunteerHomePage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_homepage);

        Button existing_farmer_btn;
        existing_farmer_btn = findViewById(R.id.existing_farmer_button);

        existing_farmer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), farmer_list.class);
                startActivity(intent);
            }
        });
    }
}
