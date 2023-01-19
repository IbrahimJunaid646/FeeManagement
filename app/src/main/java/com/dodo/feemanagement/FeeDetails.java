package com.dodo.feemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;

public class FeeDetails extends AppCompatActivity {
    AppCompatImageView onBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_details);
        getSupportActionBar().hide();

        onBack=findViewById(R.id.onBack);

        listeners();
    }
    public void listeners(){
        onBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}