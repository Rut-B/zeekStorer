package com.example.rutbiton.zeeksrorertest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class invoiceDetailsActivity extends AppCompatActivity {

    TextView storeTxt, sumTxt, categoryTxt, invoiceDateTxt, dueDateTxt;

    ImageView invoiceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        getDetails();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private  void getDetails(){
        try {
            Bundle extras = getIntent().getExtras();
            String store = extras.getString("store");
            String sum = extras.getString("sum");
            String Idate = extras.getString("date");
            String category = extras.getString("category");
            String isCredit = extras.getString("isCredit");
            String dueDate = extras.getString("dueDate");
            byte [] img = extras.getByteArray("img");
            // insert to views
            storeTxt.setText("Store: "+store);
            sumTxt.setText("Sum: "+sum);
            categoryTxt.setText(category);
            invoiceDateTxt.setText("Date: "+Idate);
            if(isCredit.equals("true")){
                //it is credit
                if(dueDate.equals("")){
                    //empty date
                    dueDateTxt.setText("Dua Date: Not Inserted");
                }
                else{
                    //due date exist
                    dueDateTxt.setText("Dua Date: "+dueDate);
                }
            }
            else{
                //it is invoice
                dueDateTxt.setVisibility(View.GONE);
            }
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            invoiceImage.setImageBitmap(bitmap);
         // invoiceImage.setTag(position);

        }catch (Exception e){
            Intent in = new Intent(invoiceDetailsActivity.this, homeFilesActivity.class);
            startActivity(in);
        }

    }

    private void init(){
        storeTxt = (TextView)findViewById(R.id.txtDetailsStore);
        sumTxt  = (TextView)findViewById(R.id.txtDetailsSum);
        categoryTxt = (TextView)findViewById(R.id.txtDetailsCategory);
        invoiceDateTxt = (TextView)findViewById(R.id.txtDetailsDate);
        dueDateTxt= (TextView)findViewById(R.id.txtDetailsDueDate);

        invoiceImage = (ImageView) findViewById(R.id.imageViewDatails);
    }
}
