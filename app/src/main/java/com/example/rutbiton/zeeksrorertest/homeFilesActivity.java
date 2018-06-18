package com.example.rutbiton.zeeksrorertest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class homeFilesActivity extends AppCompatActivity  {

    View btnPlus, btnTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init_cameraBtn();
        Log.d("debug","in cameraaaaaaaaaaaa");
    }

    private void init_cameraBtn()
    {
        setContentView(R.layout.activity_home_files);
        btnPlus= findViewById(R.id.btnPlusID);
        btnTemp= findViewById(R.id.btnCreditID);
 //~check permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            btnPlus.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(homeFilesActivity.this, newFileActivity.class);
                startActivity(in);
            }
        });
        // to delete
        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(homeFilesActivity.this, InvoiceListActivity.class);
                startActivity(in);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                btnPlus.setEnabled(true);
            }
        }
    }
}
