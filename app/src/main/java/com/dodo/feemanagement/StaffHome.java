package com.dodo.feemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class StaffHome extends AppCompatActivity {

    AppCompatImageView LogOut;
    MaterialButton addStudentButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);
        addStudentButton=findViewById(R.id.addStudentButton);

        LogOut=findViewById(R.id.LogOut);
        getSupportActionBar().hide();

        LogOut();

    }

    public void LogOut(){
        FirebaseAuth.getInstance().signOut();
        LogOut.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)) );}

    public void setListeners(){
        addStudentButton.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), AddStudent.class)));
    }
}