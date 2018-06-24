package com.example.rutbiton.zeeksrorertest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import de.keyboardsurfer.android.widget.crouton.Crouton;

public class InvoiceListActivity extends AppCompatActivity {

    ArrayList<Invoice> list;
    ListView listView;
    private static InvoiceAdapter adapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_list);
        getMassages();
        cursor = getOption();

//get all invoices
        listView=(ListView)findViewById(R.id.listIn);
        list= new ArrayList<>();

        // get all data from sqlite
        //Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM INVOICE WHERE isCredit='true'");

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String store = cursor.getString(1);
            String sum = cursor.getString(2);
            byte[] image = cursor.getBlob(3);
            String date = cursor.getString(4);
            String category = cursor.getString(5);
            String kind = cursor.getString(6);
            String dueDate = cursor.getString(7);

            list.add(new Invoice(date, store, sum, category, image,kind, dueDate, id));
        }
        Collections.reverse(list);
        adapter= new InvoiceAdapter(list,getApplicationContext());
            listView.setAdapter(adapter);
        }
private void getMassages(){
        try {
            //get and show massage

        Bundle extras = getIntent().getExtras();
        String massage = extras.getString("m");

       if(massage.equals("add")){
           Toast.makeText(getApplicationContext(), "added successfully", Toast.LENGTH_LONG).show();
           View customView = getLayoutInflater().inflate(R.layout.custom_crouton_layout, null);
           Crouton.show(InvoiceListActivity.this, customView);
       }
        }
        catch (Exception e){

        }
}
    private Cursor getOption(){
        try {
            //get and show massage

            Bundle extras = getIntent().getExtras();
            String option  = extras.getString("option");

           switch (option){
               case "invoice":{
                  return MainActivity.sqLiteHelper.getData("SELECT * FROM INVOICE WHERE isCredit='false'");
               }
               case "credit":{
                   return MainActivity.sqLiteHelper.getData("SELECT * FROM INVOICE WHERE isCredit='true'");
               }
               case "latest":{
                   return MainActivity.sqLiteHelper.getData("SELECT * FROM INVOICE LIMIT 15");
               }
           }
        }
        catch (Exception e){
            return MainActivity.sqLiteHelper.getData("SELECT * FROM INVOICE");
        }
        return MainActivity.sqLiteHelper.getData("SELECT * FROM INVOICE");
    }

    }
