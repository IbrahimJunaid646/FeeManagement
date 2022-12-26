package com.dodo.feemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

public class RemoveParent extends AppCompatActivity {

    AppCompatImageView REV;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_parent);
        getSupportActionBar().hide();

        REV=findViewById(R.id.REV);
        setListeners();
    }

    private void setListeners(){
        REV.setOnClickListener(v -> onBackPressed());
    }
}