package com.example.farmers_app_nic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public  class SignUpPage_2 extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page_2);
        Button proof_upload_btn;
        Button submit_btn;
        ImageView imageView = null;
        final Uri[] imageUri = new Uri[1];
        final String[] encodedImage = new String[1];
        proof_upload_btn = findViewById(R.id.proof_upload_btn);
        submit_btn =  findViewById(R.id.signup_submit_button);

        proof_upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);

            }
            protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                //super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
                    imageUri[0] = data.getData();
                    imageView.setImageURI(imageUri[0]);


                    Bitmap originBitmap = null;
                    Uri selectedImage = data.getData();
                    InputStream imagestream;
                    try {
                        imagestream = getContentResolver().openInputStream(selectedImage);
                        originBitmap = BitmapFactory.decodeStream(imagestream);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    if (originBitmap != null) {
                        //this.imageView.setImageBitmap(originBitmap);
                        Bitmap image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        encodedImage[0] = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                        //here we will be uploading encodedImage to the db.

                        //add db code
                        //Fetch doLogin = new Fetch();
                        //doLogin.execute("");
                        Toast.makeText(SignUpPage_2.this, "PROOF IMAGE UPLOADED SUCCESSFULLY", Toast.LENGTH_SHORT).show();



                    }
                }
            }
        });


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), VolunteerHomePage.class);
                startActivity(intent);
            }
        });



//TODO COMPLETE VALIDATION


    }




}
