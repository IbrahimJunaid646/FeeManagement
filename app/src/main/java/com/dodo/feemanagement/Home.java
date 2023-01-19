package com.dodo.feemanagement;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    MaterialButton  buttonAddStaff, buttonRemoveStaff, buttonFeeDetails;
    AppCompatImageView logout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        buttonAddStaff=findViewById(R.id.buttonAddStaff);
        buttonRemoveStaff=findViewById(R.id.buttonRemoveStaff);
        buttonFeeDetails=findViewById(R.id.buttonFeeDetails);
        logout=findViewById(R.id.logout);

        setListeners();
        Logout();

    }
    public void Logout(){
        FirebaseAuth.getInstance().signOut();
        logout.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)) );}
    private void setListeners(){
        buttonAddStaff.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), addNewStaff.class)));
        buttonRemoveStaff.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), RemoveStaff.class)));
        buttonFeeDetails.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), FeeDetails.class)));
    }

}