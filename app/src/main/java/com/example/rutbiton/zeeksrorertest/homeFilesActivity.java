package com.example.rutbiton.zeeksrorertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class homeFilesActivity extends AppCompatActivity  {

    View btnPlus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_files);
        btnPlus= findViewById(R.id.btnPlusID);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent in = new Intent(homeFilesActivity.this, latestActivity.class);
               startActivity(in);


            }
        });

    }

}
