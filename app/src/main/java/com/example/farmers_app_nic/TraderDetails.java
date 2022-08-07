package com.example.farmers_app_nic;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

public class TraderDetails extends AppCompatActivity {
public String name,father,address,phone;

  public   TextInputLayout name_ed,father_name_ed,address_ed,phone_ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trader_details);

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add_trader_btn);

         name_ed = findViewById(R.id.textInputLayout);
         father_name_ed = findViewById(R.id.textInputLayout2);
         address_ed = findViewById(R.id.textInputLayout3);
         phone_ed = findViewById(R.id.textInputLayout4);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    postUsingVolley();;


            }
        });

    }


    private void postUsingVolley() {


        name = name_ed.getEditText().getText().toString();
        father = father_name_ed.getEditText().getText().toString();
        address = address_ed.getEditText().getText().toString();
        phone = phone_ed.getEditText().getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);

        // showProgressDialog();
        System.out.println("post using volley called");
        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("tid", "1234");
        postParam.put("t_name", name);
        postParam.put("t_fathername", father);
        postParam.put("t_location", address);
        postParam.put("t_mobile", phone);
        System.out.println(name+father+address+phone);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                "http://10.11.161.194:3300/trader_post", new JSONObject(postParam),
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
}