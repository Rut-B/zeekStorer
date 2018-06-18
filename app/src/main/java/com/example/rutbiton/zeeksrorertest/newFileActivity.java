package com.example.rutbiton.zeeksrorertest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;


public class newFileActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("debug","in cameraaaaaaaaaaaa");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_file);

        imageView = (ImageView)findViewById(R.id.imageView);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  Toast.makeText(getApplicationContext(), "er000000000000000000000000000000000000000000ror!", Toast.LENGTH_LONG).show();
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        //have to add data to this image!
        imageView.setImageBitmap(bitmap);

        // move to get info
        Intent in = new Intent(newFileActivity.this, TempActivity.class);
        Bundle b = new Bundle();
        b.putByteArray("image", ImageHandler.imageViewToByte(imageView)); //
        in.putExtras(b); //Put your image to your next Intent
        startActivity(in);
        finish();
    }
}











