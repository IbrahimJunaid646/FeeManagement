package com.dodo.feemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class Home extends AppCompatActivity {

    MaterialButton  buttonAddStaff, buttonRemoveStaff, buttonFeeDetails;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        buttonAddStaff=findViewById(R.id.buttonAddStaff);
        buttonRemoveStaff=findViewById(R.id.buttonRemoveStaff);
        buttonFeeDetails=findViewById(R.id.buttonFeeDetails);
        setListeners();
    }

    private void setListeners(){
        buttonAddStaff.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), addNewStaff.class)));
        buttonRemoveStaff.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), RemoveStaff.class)));
        buttonFeeDetails.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), FeeDetails.class)));

    }

}