package com.dodo.feemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";

    MaterialButton signInButton, sendEmail, cancel;
    EditText email, password, emailLink;
    ProgressBar loginProgressbar;
    FirebaseAuth FrAuth;
    TextView buttonForgetPassword;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        signInButton = findViewById(R.id.signInButton);
        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        loginProgressbar = findViewById(R.id.loginProgressbar);
        buttonForgetPassword = findViewById(R.id.buttonForgotPassword);
        FrAuth = FirebaseAuth.getInstance();


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().contains("admin@apptech.com") && password.getText().toString().contains("123456")) {
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                    finish();
                }


                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();

                if (TextUtils.isEmpty(Email)) {
                    email.setError("Enter Valid Email");
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    password.setError("Enter Valid Password");
                    return;
                }
                if (Password.length() < 6) {
                    password.setError("Password Must be >= 6 Character");
                    return;
                }
                loginProgressbar.setVisibility(View.VISIBLE);
                signInButton.setVisibility(View.GONE);

                FrAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), StaffHome.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                loginProgressbar.setVisibility(View.GONE);
                                signInButton.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });


            }
        });

        buttonForgetPassword.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog= new AlertDialog.Builder(MainActivity.this);
                View view=getLayoutInflater().inflate(R.layout.alert_dialog, null);
                EditText emailLink= (EditText) view.findViewById(R.id.emailLink);
                MaterialButton sendEmail= (MaterialButton) view.findViewById(R.id.sendEmail);
                MaterialButton cancel= (MaterialButton) view.findViewById(R.id.cancel);
                alertDialog.setView(view);
                alertDialog.setCancelable(true);
                AlertDialog dialog= alertDialog.create();
                view.setBackgroundResource(R.color.colorFieldBackground);







                /*tetsgafsg*/


                sendEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mail=emailLink.getText().toString();
                        FrAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(MainActivity.this, "Reset link sent to your email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error!! Email not found"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }
}