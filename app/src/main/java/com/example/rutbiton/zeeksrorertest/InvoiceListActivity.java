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

import java.util.ArrayList;

public class InvoiceListActivity extends AppCompatActivity {

    ArrayList<Invoice> list;
    ListView listView;
    private static InvoiceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_list);
            listView=(ListView)findViewById(R.id.listIn);
            list= new ArrayList<>();

        // get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM INVOICE");

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String store = cursor.getString(1);
            String sum = cursor.getString(2);
            byte[] image = cursor.getBlob(3);
            String date = cursor.getString(4);
            String category = cursor.getString(5);

            list.add(new Invoice(date, store, sum, category, image,true, id));
        }

        adapter= new InvoiceAdapter(list,getApplicationContext());
            listView.setAdapter(adapter);
        }


    }
