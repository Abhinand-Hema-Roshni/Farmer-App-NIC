package com.example.farmers_app_nic;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class SignUpPage_2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText first_name,last_name;  //TODO will be sending to db
    EditText address_ed, occupation_ed;
    RadioGroup proof_group;
    ActivityResultLauncher activityResultLauncher;
    Spinner spinner;
    Spinner spinner_village, spinner_mandal;
    ArrayAdapter <String> MandalAdapter;
    ArrayList <String> MandalArray;
    ArrayList <String> villageArray;

  public  String fname,lname,address,occ,district,mandal,village,ptype,pfile;


    RadioButton proof_button;
    private static final int RESULT_LOAD_IMAGE=1;
    private static final int CAMERA_ACTION_CODE=1;

    public static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page_2);
        Button proof_upload_btn;
        Button submit_btn;
        ImageView imageView ;

        DatabaseAccess dba = DatabaseAccess.getInstance(this);
        dba.open();
       ArrayList <String> DistrictArray = new ArrayList<>();
       DistrictArray=dba.getDistrict();




        spinner = findViewById(R.id.district_spinner);
        spinner_mandal = findViewById(R.id.mandal_spinner);
spinner_village = findViewById(R.id.village_spinner);


        ArrayAdapter<String> DistrictAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,DistrictArray);
       DistrictAdapter.addAll(dba.getDistrict());
        DistrictAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
       spinner.setAdapter(DistrictAdapter);
       dba.close();


       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String District_name = spinner.getSelectedItem().toString();
               dba.open();

               MandalArray = dba.getMandalfromDistrict(District_name);
               System.out.println("mandal array"+MandalArray);
               ArrayAdapter<String> MandalAdapter = new ArrayAdapter<String>(SignUpPage_2.this,R.layout.support_simple_spinner_dropdown_item);

               MandalAdapter.clear();
               MandalAdapter.addAll(MandalArray);
               MandalAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);


               spinner_mandal.setAdapter(MandalAdapter);






           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

       spinner_mandal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String mandal_name = spinner_mandal.getSelectedItem().toString();
               dba.open();

               villageArray = dba.getVillagefromMandal(mandal_name);
               System.out.println("village array"+villageArray);
               ArrayAdapter<String> VillageAdapter = new ArrayAdapter<String>(SignUpPage_2.this,R.layout.support_simple_spinner_dropdown_item);

               VillageAdapter.clear();
               VillageAdapter.addAll(villageArray);
               VillageAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);


               spinner_village.setAdapter(VillageAdapter);
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
//
//
//





        first_name = findViewById(R.id.sup_firstName_editText);
        last_name = findViewById(R.id.sup_lastName_editText);
        address_ed = findViewById(R.id.address_editText);
        occupation_ed = findViewById(R.id.occupation_editText);


        imageView  = findViewById(R.id.proof_imageview);

        proof_group=(RadioGroup)findViewById(R.id.proof_adiogroup);



        final Uri[] imageUri = new Uri[1];
        final String[] encodedImage = new String[1];
        proof_upload_btn = findViewById(R.id.proof_upload_btn);
        submit_btn =  findViewById(R.id.signup_submit_button);





        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imageView.setImageBitmap(bitmap);
                }
            }
        });


        proof_upload_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        System.out.println("button clicked");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            activityResultLauncher.launch(intent);
            System.out.println("in if");
        } else {
            Toast.makeText(SignUpPage_2.this, "There is no app that support this action",
                    Toast.LENGTH_SHORT).show();
            System.out.println("in else");
        }
    }
});








//        proof_upload_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(gallery, RESULT_LOAD_IMAGE);
//              //  someActivityResultLauncher.launch(intent);
//
//
//
//            }
//
//
//
//
//
//            protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//              //  super.onActivityResult(requestCode, resultCode, data);
//                if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMAGE && data != null) {
//
//                    Uri selectedimage = data.getData();
//                    imageView.setImageURI(selectedimage);
//                    System.out.println("image is " + data.getData().toString());
//
//
////
////                    imageUri[0] = data.getData();
////                    imageView.setImageURI(imageUri[0]);
////
////
////                    Bitmap originBitmap = null;
////                    Uri selectedImage = data.getData();
////                    InputStream imagestream;
////                    try {
////                        imagestream = getContentResolver().openInputStream(selectedImage);
////                        originBitmap = BitmapFactory.decodeStream(imagestream);
////                    } catch (FileNotFoundException e) {
////                        e.printStackTrace();
////                    }
////
////                    if (originBitmap != null) {
////                        //this.imageView.setImageBitmap(originBitmap);
////                        Bitmap image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
////                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
////                        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
////                        encodedImage[0] = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
////                        //here we will be uploading encodedImage to the db.
////
////                        System.out.println("endoded image is "+encodedImage);
////
////                        //add db code
////                        //Fetch doLogin = new Fetch();
////                        //doLogin.execute("");
////                        Toast.makeText(SignUpPage_2.this, "PROOF IMAGE UPLOADED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
////                        System.out.println("PROOF IMAGE UPLOADED SUCCESSFULLY");
////
////
////                    }
//                }
//            }
//        });


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(view.getContext(), VolunteerHomePage.class);
                startActivity(intent);


                int selectedId=proof_group.getCheckedRadioButtonId();
                proof_button=(RadioButton)findViewById(selectedId);
                Toast.makeText(SignUpPage_2.this,proof_button.getText(),Toast.LENGTH_SHORT).show();

                RequestQueue queue = Volley.newRequestQueue(SignUpPage_2.this);
                String url = "http://10.11.161.194:3300/user_details_insert";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
//                        textView.setText("Response is: " + response.substring(0,500));\
                                System.out.println(response);
                                Toast.makeText(SignUpPage_2.this, "Response"+response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        Toast.makeText(SignUpPage_2.this, "error in connection", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(stringRequest);
                postUsingVolley();






                System.out.println("selected"+proof_button.getText().toString());



//TODO
//                Intent intent = new Intent(view.getContext(), VolunteerHomePage.class);
//                startActivity(intent);
            }
        });



//TODO COMPLETE VALIDATION







    }

    private void postUsingVolley() {


        fname = first_name.getText().toString();
        lname = last_name.getText().toString();
        address = address_ed.getText().toString();
        occ = occupation_ed.getText().toString();
        ptype = proof_button.getText().toString();
        district = spinner.getSelectedItem().toString();
        mandal = spinner_mandal.getSelectedItem().toString();
        village = spinner_village.getSelectedItem().toString();

        RequestQueue queue = Volley.newRequestQueue(this);

        // showProgressDialog();
        System.out.println("post using volley called");
        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("u_first_name", fname);
        postParam.put("u_last_name", lname);
        postParam.put("u_address", address);
        postParam.put("u_occupation", occ);
        postParam.put("u_proof_type", ptype);
        postParam.put("u_proof_file", "file.jpg");
        postParam.put("u_district", district);
        postParam.put("u_mandal", mandal);
        postParam.put("u_village", village);
        postParam.put("u_id", "22");
        postParam.put("u_phone", "6379844154");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                "http://10.11.161.194:3300/user_details_insert", new JSONObject(postParam),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        System.out.println(response.toString());
                        // hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //hideProgressDialog();
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }



        };

        jsonObjReq.setTag(TAG);
        // Adding request to request queue
        queue.add(jsonObjReq);

        // Cancelling request
    /* if (queue!= null) {
    queue.cancelAll(TAG);
    } */







    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // On selecting a spinner item
        String label = adapterView.getItemAtPosition(i).toString();

        // Showing selected spinner item
        Toast.makeText(adapterView.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


//
//    public void chackRadio(View v){
//
//
//
//
//    }




}
