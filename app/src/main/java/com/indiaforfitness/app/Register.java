package com.indiaforfitness.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {
    private EditText mEmail;
    private EditText mName;
    private EditText mPassword;
    private EditText mPhone;
    private EditText mConfirmPassword;
    private Button mNext;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPhone= (EditText) findViewById(R.id.registerNumber);
        mNext = (Button) findViewById(R.id.nextOtpButton);
        mLogin= (Button)  findViewById(R.id.loginButton);
        final ProgressBar progressBar= (ProgressBar) findViewById(R.id.progressBar);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPhone.getText().toString().trim().isEmpty()){
                    Toast.makeText(Register.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();

                }else {

                    progressBar.setVisibility(View.VISIBLE);
                    mNext.setVisibility(View.INVISIBLE);


                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" +mPhone.getText().toString().trim(),
                            60,
                            TimeUnit.SECONDS,
                            Register.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.GONE);
                                    mNext.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        mNext.setVisibility(View.VISIBLE);
                                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressBar.setVisibility(View.GONE);
                                    mNext.setVisibility(View.VISIBLE);

                                    Intent verifyIntent= new Intent(Register.this, OTPActivity.class);
                                    verifyIntent.putExtra("mobile", mPhone.getText().toString().trim());
                                    verifyIntent.putExtra("verificationId", verificationId);
                                    startActivity(verifyIntent);
                                }

                            }
                    );





                }
            }
        });


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Register.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
            }
        });


    }
}