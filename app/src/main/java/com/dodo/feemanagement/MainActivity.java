package com.dodo.feemanagement;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    MaterialButton signInButton;
    EditText email, password;
    ProgressBar loginProgressbar;
    FirebaseAuth FrAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        signInButton=findViewById(R.id.signInButton);
        email=findViewById(R.id.emailText);
        password=findViewById(R.id.passwordText);
        loginProgressbar=findViewById(R.id.loginProgressbar);
        FrAuth=FirebaseAuth.getInstance();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().contains("admin@apptech.com") && password.getText().toString().contains("123456"))
                {Intent intent=new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                    finish();}


                String Email= email.getText().toString().trim();
                String Password= password.getText().toString().trim();

                if (TextUtils.isEmpty(Email)){
                    email.setError("Enter Valid Email");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    password.setError("Enter Valid Password");
                    return;
                }
                if (Password.length() <6){
                    password.setError("Password Must be >= 6 Character");
                    return;
                }
                loginProgressbar.setVisibility(View.VISIBLE);
                signInButton.setVisibility(View.GONE);

                FrAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(), StaffHome.class);
                            startActivity(i);
                            finish();
                        }else {
                            Toast.makeText(MainActivity.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            loginProgressbar.setVisibility(View.GONE);
                            signInButton.setVisibility(View.VISIBLE);
                        }}
                    }
                });



            }
        });
    }
}