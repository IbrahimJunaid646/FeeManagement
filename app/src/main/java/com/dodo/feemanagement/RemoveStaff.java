package com.dodo.feemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

public class RemoveStaff extends AppCompatActivity {

    AppCompatImageView Rev;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_staff);
        getSupportActionBar().hide();

        Rev=findViewById(R.id.Rev);
        setListners();
    }

    private void setListners(){
        Rev.setOnClickListener(v -> onBackPressed());
    }
}