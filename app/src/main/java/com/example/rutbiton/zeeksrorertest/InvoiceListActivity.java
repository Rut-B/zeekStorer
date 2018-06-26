package com.example.rutbiton.zeeksrorertest;

import android.content.Intent;
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

public class InvoiceListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {

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
        listView.setOnItemClickListener(this);
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


    @Override

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Invoice item = (Invoice) adapterView.getItemAtPosition(position);

        // Construct an Intent as normal
        Intent in = new Intent(this, invoiceDetailsActivity.class);
        Bundle b = new Bundle();
        b.putString("store",item.getStore()); //
        b.putString("sum",item.getSum());
        b.putString("category",item.getCategory());
        b.putString("date",item.getDate());
        b.putString("isCredit",item.getIsCredit());
        b.putString("dueDate",item.getDueDate());
        b.putByteArray("img",item.getImage());
        in.putExtras(b);
        startActivity(in);
      //  intent.putExtra(DetailActivity.EXTRA_PARAM_ID, item.getId());


       /* ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,

                // Now we provide a list of Pair items which contain the view we can transitioning
                // from, and the name of the view it is transitioning to, in the launched activity
                new Pair<View, String>(view.findViewById(R.id.imageview_item),
                        DetailActivity.VIEW_NAME_HEADER_IMAGE),
                new Pair<View, String>(view.findViewById(R.id.textview_name),
                        DetailActivity.VIEW_NAME_HEADER_TITLE));

        // Now we can start the Activity, providing the activity options as a bundle
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
        // END_INCLUDE(start_activity)*/

    }



    }
