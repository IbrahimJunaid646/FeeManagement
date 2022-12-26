package com.dodo.feemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class Home extends AppCompatActivity {

    MaterialButton buttonAddStudent, buttonAddStaff, buttonAddParent,
            buttonRemoveStudent, buttonRemoveStaff, buttonRemoveParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        buttonAddStudent=findViewById(R.id.buttonAddStudent);
        buttonAddStaff=findViewById(R.id.buttonAddStaff);
        buttonAddParent=findViewById(R.id.buttonAddParent);
        buttonRemoveStudent=findViewById(R.id.buttonRemoveStudent);
        buttonRemoveStaff=findViewById(R.id.buttonRemoveStaff);
        buttonRemoveParent=findViewById(R.id.buttonRemoveParent);

        setListeners();
    }

    private void setListeners(){
        buttonAddStaff.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), addNewStaff.class)));
        buttonAddStudent.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), AddStudent.class)));
        buttonAddParent.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), AddParent.class)));
    }

}