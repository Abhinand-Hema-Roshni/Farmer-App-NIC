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

        Button existing_farmer_btn, sign_out_btn, new_trader_btn,new_farmer_button;
        existing_farmer_btn = findViewById(R.id.existing_farmer_button);
        sign_out_btn = findViewById(R.id.sign_out_button);
        new_trader_btn=findViewById(R.id.new_trader_button);
        new_farmer_button=findViewById(R.id.new_farmer_button);

        //new trader
        new_trader_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(view.getContext(),TraderDetails.class));
            }
        });

        //new farmer
        new_farmer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),FarmerDetails.class));
            }
        });

        //existing farmer
        existing_farmer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), farmer_list.class);
                startActivity(intent);
            }
        });

        //sign out
        sign_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
