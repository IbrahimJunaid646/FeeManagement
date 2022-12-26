package com.dodo.feemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;

public class RemoveStudent extends AppCompatActivity {

    AppCompatImageView rev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_student);

        getSupportActionBar().hide();

        rev=findViewById(R.id.rev);
        setListeners();
    }

    private void setListeners(){
        rev.setOnClickListener(v -> onBackPressed());
    }
}