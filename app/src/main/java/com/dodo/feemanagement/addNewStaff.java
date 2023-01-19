package com.dodo.feemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addNewStaff extends AppCompatActivity {

    AppCompatImageView buttonback;
    EditText email, password, staffname, staffid;
    FirebaseAuth fAuth;
    ProgressBar ProgressBar;
    FirebaseFirestore fstore;
    MaterialButton adduserbutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_staff);
        getSupportActionBar().hide();
        buttonback=findViewById(R.id.button);
        email=findViewById(R.id.emailStaff);
        password=findViewById(R.id.passwordStaff);
        staffname=findViewById(R.id.nameStaff);
        staffid=findViewById(R.id.idStaff);
        adduserbutton=findViewById(R.id.addStaffButton);


        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Yes Bro");
                onBackPressed();
            }
        });
        adduserbutton.setOnClickListener(new View.OnClickListener() {
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
                adduserbutton.setVisibility(View.GONE);


                fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(addNewStaff.this, "User Created", Toast.LENGTH_SHORT).show();
                            ProgressBar.setVisibility(View.GONE);
                            adduserbutton.setVisibility(View.VISIBLE);
                        FirebaseUser user=fAuth.getCurrentUser();
                        Toast.makeText(addNewStaff.this, "User Created", Toast.LENGTH_SHORT).show();
                        DocumentReference df=fstore.collection("Users").document(user.getUid());
                        Map<String,Object> userInfo=new HashMap<>();
                        userInfo.put("FullName",staffname.getText().toString());
                        userInfo.put("StaffEmail",email.getText().toString());
                        userInfo.put("StaffId",staffid.getText().toString());
                        userInfo.put("StaffPassword",password.getText().toString());
                        userInfo.put("isTeacher","1");
                        df.set(userInfo);
                        email.setText("");
                        password.setText("");
                        staffid.setText("");
                        staffname.setText("");
                    }else {
                        Toast.makeText(addNewStaff.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> System.out.println(e));
            }
        });
    }
}