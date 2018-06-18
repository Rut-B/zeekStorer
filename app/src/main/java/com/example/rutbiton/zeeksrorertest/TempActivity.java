package com.example.rutbiton.zeeksrorertest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class TempActivity extends AppCompatActivity {

    EditText edtStore, edtSum, edtDate;
    Button btnSave;
    ImageView imageView;
    DatePicker dueDate;
    CheckBox haveDueDate;
    Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        init();
        //get image
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("image");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) findViewById(R.id.imageView);

        image.setImageBitmap(bmp);
       haveDueDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(haveDueDate.isChecked()){
                    dueDate.setVisibility(View.VISIBLE);
                }
                else{
                    dueDate.setVisibility(View.GONE);
                }
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    MainActivity.sqLiteHelper.insertData(
                            edtStore.getText().toString().trim(),
                            edtSum.getText().toString().trim(),
                            edtDate.getText().toString().trim(),
                           ImageHandler.imageViewToByte(imageView),
                            spinner.getSelectedItem().toString(),
                            "true"
                    );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    edtStore.setText("");
                    edtSum.setText("");
                    edtSum.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }






    private void init(){
        edtDate = (EditText) findViewById(R.id.edtDay);
        dueDate = (DatePicker) findViewById(R.id.dueDate);
        edtStore = (EditText)findViewById(R.id.edtStore) ;
        edtSum = (EditText)findViewById(R.id.edtSum) ;
        imageView = (ImageView) findViewById(R.id.imageView);
        haveDueDate = (CheckBox) findViewById(R.id.checkDueDate);
        btnSave = (Button)findViewById(R.id.btnSave);


        spinner = (Spinner) findViewById(R.id.categorySpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}
