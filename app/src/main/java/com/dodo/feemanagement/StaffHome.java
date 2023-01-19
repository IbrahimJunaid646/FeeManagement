package com.dodo.feemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class StaffHome extends AppCompatActivity {

    AppCompatImageView LogOut;
    MaterialButton insertfeebtn, markattendencebtn,viewstudentbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);
        LogOut=findViewById(R.id.LogOut);
        getSupportActionBar().hide();
       insertfeebtn = findViewById(R.id.insertfee);
       markattendencebtn = findViewById(R.id.markattendence);
       viewstudentbtn= findViewById(R.id.viewstudent);

        insertfeebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Int=new Intent(getApplicationContext(),InsertFee.class);
                startActivity(Int);}});
        markattendencebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),MarkAttendence.class);
                startActivity(in);}});
        viewstudentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Nint=new Intent(getApplicationContext(),ViewStudent.class);
                startActivity(Nint);}});

        LogOut();
    }

    public void LogOut(){
        FirebaseAuth.getInstance().signOut();
        LogOut.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)) );}

}