package com.example.farmers_app_nic;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //Hi there, just checking if its working lolol

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signup_btn;
        signup_btn = findViewById(R.id.sign_up_button);

        //db instance

       // Database db =new Database();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.11.161.194:3300/crop_nature_details";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: " + response.substring(0,500));\
                        System.out.println(response);
                        Toast.makeText(MainActivity.this, "Response"+response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                Toast.makeText(MainActivity.this, "error in connection", Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);









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
        //String lurl = "http://10.11.161.194:3300/crop_nature_details";
       postUsingVolley();



    }





    public void postUsingVolley(){
        RequestQueue queue = Volley.newRequestQueue(this);

           // showProgressDialog();


            Map<String, String> postParam= new HashMap<String, String>();
            postParam.put("c_nature_id", "307307");
            postParam.put("c_nature", "sericulture");
        postParam.put("c_nature_local", "local_sericulture");


            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    "http://10.11.161.194:3300/crop_nature_details_post", new JSONObject(postParam),
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













//    public void postUsingVolley(String url) {
//
//
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest request = new StringRequest(Request.Method.POST, "http://10.11.161.194:3300/crop_nature_details_post", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                System.out.println("My success"+response);
//                if(response.equalsIgnoreCase("true")) {
//
//                    System.out.println("true!!");
//                    Toast.makeText(MainActivity.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
//                }else
//                {
//                    //credential not match
//                    System.out.println("could not authenticate");
//                    Toast.makeText(MainActivity.this,"Try Again",Toast.LENGTH_LONG).show();
//                }
//            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.print("My error"+error);
//                Toast.makeText(MainActivity.this, "my error :"+error, Toast.LENGTH_LONG).show();
//              //  System.out.print("My error"+error);
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() {
//
//                final HashMap<String, String> postParams = new HashMap<String, String>();
//               postParams.put("c_nature_id", "6");
//                postParams.put("c_nature", "horti");
//                postParams.put("c_nature_local", "null");
//                return postParams;
//            }
//        };
//        queue.add(request);
//        }


}