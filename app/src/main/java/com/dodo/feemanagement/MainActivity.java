package com.dodo.feemanagement;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    MaterialButton signInButton;
    EditText email, password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        signInButton=findViewById(R.id.signInButton);
        email=findViewById(R.id.emailText);
        password=findViewById(R.id.passwordText);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().contains("admin@apptech.com") && password.getText().toString().contains("123456"))
                {Intent intent=new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                    finish();}else {
                    Toast.makeText(MainActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}