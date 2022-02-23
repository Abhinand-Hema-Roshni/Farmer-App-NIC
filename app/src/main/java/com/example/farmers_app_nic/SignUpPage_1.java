package com.example.farmers_app_nic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUpPage_1 extends AppCompatActivity {

//TODO ADD A NEW COLUMN TO THE C_TYPE TABLE IN POSTGRES
    EditText first_name,last_name;  //TODO will be sending to db
    EditText mobile_number, otp_entered;
    String phonenumber; //TODO we will be sending to db
    String otpid;
    Button send_otp;
    Button verify_otp_btn;
    Button testBTN; //TODO remove
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page_1);

        testBTN = findViewById(R.id.testBTN); //TODO remove
        verify_otp_btn = findViewById(R.id.verify_otp_button);
        send_otp = findViewById(R.id.sup_sendotp_button);
        first_name = findViewById(R.id.sup_firstName_editText);
        last_name = findViewById(R.id.sup_lastName_editText);
        mobile_number = findViewById(R.id.sup_phone_editText);
        otp_entered = findViewById(R.id.sup_otp_editText);
        mAuth=FirebaseAuth.getInstance();

        send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phonenumber = mobile_number.getText().toString();

                initiateotp();
            }
        });
         //TODO Remove code block
        testBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SignUpPage_2.class);
                startActivity(intent);
            }
        }); //REMOVE TILL HERE



        verify_otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otp_entered.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Blank Field can not be processed",Toast.LENGTH_LONG).show();
                else if(otp_entered.getText().toString().length()!=6)
                    Toast.makeText(getApplicationContext(),"INvalid OTP",Toast.LENGTH_LONG).show();
                else
                {
                    PhoneAuthCredential credential=PhoneAuthProvider.getCredential(otpid,otp_entered.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                    System.out.println("success");


                    Intent intent = new Intent(view.getContext(), SignUpPage_2.class);
                    startActivity(intent);


                }
            }
        });
    }

    private void initiateotp() {


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                {
                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
                    {
                        otpid=s;
                    }



                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
                    {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"CORRECT",Toast.LENGTH_LONG).show();
                            //startActivity(new Intent(manageotp.this,dashboard.class));
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(),"Signin Code Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
