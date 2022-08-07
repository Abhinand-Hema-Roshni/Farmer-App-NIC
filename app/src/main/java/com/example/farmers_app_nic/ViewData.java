package com.example.farmers_app_nic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {
Button view_data;
Spinner analytics_spinner;
ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        view_data = findViewById(R.id.view_alytics_btn);
        analytics_spinner = findViewById(R.id.analytics_spinner);
        imageView = findViewById(R.id.analytics_image);







        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Top 10 states with agriculture");
        arrayList.add("Which is the most common Crop");
        arrayList.add("Which is the most common Market");



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        analytics_spinner.setAdapter(arrayAdapter);

        analytics_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tutorialsName = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(ViewData.this, "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();



                if (tutorialsName == "Top 10 states with agriculture"){
                    imageView.setImageResource(R.drawable.img);
                }
                else if (tutorialsName == "Which is the most common Crop"){
                    imageView.setImageResource(R.drawable.img_1);
                }
                else if (tutorialsName == "Which is the most common Market"){
                    imageView.setImageResource(R.drawable.img_2);
                }







            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}