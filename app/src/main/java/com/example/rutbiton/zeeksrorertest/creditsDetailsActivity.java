package com.example.rutbiton.zeeksrorertest;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class creditsDetailsActivity extends AppCompatActivity {

    EditText edtStore, edtSum;
    Button btnSave;
    ImageView imageView;
    DatePicker date;
    DatePicker dueDate;
    CheckBox haveDueDate;

  //  public static SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_details);
    }
/*

        init();

        sqLiteHelper = new SQLiteHelper(this, "InvoiceDB.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS INVOICE(Id INTEGER PRIMARY KEY AUTOINCREMENT, store VARCHAR, sum VARCHAR, image BLOB,date VARCHAR,isCredit VARCHAR)");

        haveDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    int day = date.getDayOfMonth();
                    int month = date.getMonth() + 1;
                    int year = date.getYear();
                    sqLiteHelper.insertData(
                            edtStore.getText().toString().trim(),
                            edtSum.getText().toString().trim(),
                            "" + day+ month + year,
                            imageViewToByte(imageView),
                            "true"
                    );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    edtStore.setText("");
                    edtSum.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }*/

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void init(){
        date = (DatePicker) findViewById(R.id.invoiceDate);
        dueDate = (DatePicker) findViewById(R.id.dueDate);
        edtStore = (EditText)findViewById(R.id.edtStore) ;
        edtSum = (EditText)findViewById(R.id.edtSum) ;
        imageView = (ImageView) findViewById(R.id.imageView);
        haveDueDate = (CheckBox) findViewById(R.id.checkDueDate);
    }







}
