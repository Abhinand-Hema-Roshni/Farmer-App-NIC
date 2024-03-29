package com.example.farmers_app_nic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignInPage extends AppCompatActivity {

//
//    Button signin_btn;
//    Button send_otp;
//    EditText mobile_number, otp_entered;
//    String phonenumber;
//    String otpid;
//    FirebaseAuth mAuth;


    // variable for FirebaseAuth class
    private FirebaseAuth mAuth;

    // variable for our text input
    // field for phone and OTP.
    private EditText edtPhone, edtOTP;

    // buttons for generating OTP and verifying OTP
    private Button verifyOTPBtn, generateOTPBtn;
    Button skipper;

    // string for storing our verification ID
    private String verificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.sign_in_page);
//
//        //declaring the relation w xml page
//        send_otp = findViewById(R.id.send_otp);
//        mobile_number  = findViewById(R.id.mobile_editText);
//        otp_entered = findViewById(R.id.otp_editText);
//        signin_btn = findViewById(R.id.signin_submit_button);
//        mAuth=FirebaseAuth.getInstance();

        //get texts

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_page);

        // below line is for getting instance
        // of our FirebaseAuth.
        mAuth = FirebaseAuth.getInstance();

        // initializing variables for button and Edittext.
        edtPhone = findViewById(R.id.mobile_editText);
        edtOTP = findViewById(R.id.otp_editText);
        verifyOTPBtn = findViewById(R.id.signin_submit_button);
        generateOTPBtn = findViewById(R.id.send_otp);
skipper = findViewById(R.id.skip_btn);

//  //sign in button
//        send_otp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                phonenumber = mobile_number.getText().toString();
//
//                if (TextUtils.isEmpty(mobile_number.getText().toString())) {
//                    // when mobile number text field is empty
//                    // displaying a toast message.
//                    Toast.makeText(SignInPage.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
//                } else {
//                    // if the text field is not empty we are calling our
//                    // send OTP method for getting OTP from Firebase.
//                    String phone = "+91" + mobile_number.getText().toString();
//                    sendVerificationCode(phone);
//                }
//
//
//
////
////                //TODO: query the postgres db to check of the number exists then only intiate otp
////               //initiateotp();
////                Toast.makeText(SignInPage.this, "send button pressed", Toast.LENGTH_SHORT).show();
////                System.out.println("send button pressed");
//////                Intent intent = new Intent(view.getContext(), VolunteerHomePage.class);
//////                startActivity(intent);
//            }
//        });


        skipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInPage.this, VolunteerHomePage.class);
                startActivity(i);
            }
        });

        // setting onclick listener for generate OTP button.
        generateOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is for checking weather the user
                // has entered his mobile number or not.
                if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                    // when mobile number text field is empty
                    // displaying a toast message.
                    Toast.makeText(SignInPage.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    // if the text field is not empty we are calling our
                    // send OTP method for getting OTP from Firebase.
                    String phone = "+91" + edtPhone.getText().toString();
                    sendVerificationCode(phone);
                }
            }
        });


//
//        signin_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                // validating if the OTP text field is empty or not.
//                if (TextUtils.isEmpty(otp_entered.getText().toString())) {
//                    // if the OTP text field is empty display
//                    // a message to user to enter OTP
//                    Toast.makeText(SignInPage.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
//                } else {
//                    // if OTP field is not empty calling
//                    // method to verify the OTP.
//                    verifyCode(otp_entered.getText().toString());
//                }
//
//
////                System.out.println("click signin");
////              if(otp_entered.getText().toString().isEmpty())
////                    Toast.makeText(getApplicationContext(),"Blank Field can not be processed",Toast.LENGTH_LONG).show();
////                else if(otp_entered.getText().toString().length()!=6)
////                    Toast.makeText(getApplicationContext(),"INvalid OTP",Toast.LENGTH_LONG).show();
////                else
////                {
////
////                    System.out.println("signin pressed");
////             Intent intent = new Intent(view.getContext(), VolunteerHomePage.class);
////            startActivity(intent);
////
////
////
////                }
//
//            }
//        });


        // for verify otp button
        verifyOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the OTP text field is empty or not.
                if (TextUtils.isEmpty(edtOTP.getText().toString())) {
                    // if the OTP text field is empty display
                    // a message to user to enter OTP
                    Toast.makeText(SignInPage.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    // if OTP field is not empty calling
                    // method to verify the OTP.
                    verifyCode(edtOTP.getText().toString());
                }
            }
        });


//to send the otp
//        signin_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(otp_entered.getText().toString().isEmpty())
//                    Toast.makeText(getApplicationContext(),"Blank Field can not be processed",Toast.LENGTH_LONG).show();
//                else if(otp_entered.getText().toString().length()!=6)
//                    Toast.makeText(getApplicationContext(),"INvalid OTP",Toast.LENGTH_LONG).show();
//                else
//                {
//                    PhoneAuthCredential credential=PhoneAuthProvider.getCredential(otpid,otp_entered.getText().toString());
//                    signInWithPhoneAuthCredential(credential);
//                    System.out.println("success");
//
//
//             Intent intent = new Intent(view.getContext(), VolunteerHomePage.class);
//            startActivity(intent);
//
//
//                }
//
//            }
//        });

    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                            Intent i = new Intent(SignInPage.this, VolunteerHomePage.class);
                            startActivity(i);
                            finish();
                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(SignInPage.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                edtOTP.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(SignInPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }
}





























//        private void initiateotp() {
//
//        System.out.println("inside initiate");
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                phonenumber,        // Phone number to verify
//                60,                 // Timeout duration
//                TimeUnit.SECONDS,   // Unit of timeout
//                this,               // Activity (for callback binding)
//                new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
//                {
//                    @Override
//                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
//                    {
//
//                        otpid=s;
//                    }
//
//
//
//                    @Override
//                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
//                    {
//                        System.out.println("verified");
//                        signInWithPhoneAuthCredential(phoneAuthCredential);
//                    }
//
//                    @Override
//                    public void onVerificationFailed(FirebaseException e) {
//                        System.out.println("failed to send");
//                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                });
//    }
//
//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful())
//                        {
//                            Toast.makeText(getApplicationContext(),"CORRECT",Toast.LENGTH_LONG).show();
//                            //startActivity(new Intent(manageotp.this,dashboard.class));
//                            finish();
//
//                        } else {
//                            Toast.makeText(getApplicationContext(),"Signin Code Error",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//    }
//}
