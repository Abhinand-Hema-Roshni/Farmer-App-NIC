package com.example.farmers_app_nic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signup_btn;
        signup_btn = findViewById(R.id.sign_up_button);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SignUpPage_1.class);
                startActivity(intent);
            }
        });

        Button signin_btn;
        signin_btn = findViewById(R.id.sign_in_button);

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SignInPage.class);
                startActivity(intent);
            }
        });




    }
}