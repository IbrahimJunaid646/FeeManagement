package com.dodo.feemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class addNewStaff extends AppCompatActivity {

    AppCompatImageView buttonback;
    EditText email, password, staffName, staffID;
    FirebaseAuth fAuth;
    ProgressBar ProgressBar;
    MaterialButton addUserButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_staff);
        getSupportActionBar().hide();

        buttonback=findViewById(R.id.button);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        staffName=findViewById(R.id.staffName);
        staffID=findViewById(R.id.staffId);
        addUserButton=findViewById(R.id.addUserButton);


        fAuth=FirebaseAuth.getInstance();
        ProgressBar=findViewById(R.id.addUserPbar);

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                ProgressBar.setVisibility(View.VISIBLE);
                addUserButton.setVisibility(View.GONE);


                fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(addNewStaff.this, "User Created", Toast.LENGTH_SHORT).show();
                            ProgressBar.setVisibility(View.GONE);
                            addUserButton.setVisibility(View.VISIBLE);
                        }else {
                            Toast.makeText(addNewStaff.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}