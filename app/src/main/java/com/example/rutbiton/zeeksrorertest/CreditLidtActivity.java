package com.example.rutbiton.zeeksrorertest;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class CreditLidtActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Invoice> list;
    CreditListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_lidt);

        listView = (ListView) findViewById(R.id.listCr);
        list = new ArrayList<>();
        list.add(new Invoice("yfct", "hvyg", "juubib", "gvngnvn", null,true, 1));

        // get all data from sqlite
        //Cursor cursor = TempActivity.sqLiteHelper.getData("SELECT * FROM INVOICE");


        //list.clear();
        /*while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String store = cursor.getString(1);
            String sum = cursor.getString(2);
            byte[] image = cursor.getBlob(3);
            String date = cursor.getString(4);
            String category = cursor.getString(5);

            list.add(new Invoice(date, store, sum, category, image,true, id));
        }*/
       adapter= new CreditListAdapter(list,getApplicationContext());

        listView.setAdapter(adapter);

    }


}

     /*
        // get all data from sqlite
       Cursor cursor = TempActivity.sqLiteHelper.getData("SELECT * FROM INVOICE");
       // Toast.makeText(getApplicationContext(), "error!", Toast.LENGTH_SHORT).show();

        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String store = cursor.getString(1);
            String sum = cursor.getString(2);
            byte[] image = cursor.getBlob(3);
            String date = cursor.getString(4);
            String category = cursor.getString(5);

            list.add(new Invoice(date, store, sum, category, image,true, id));
        }
        adapter.notifyDataSetChanged();

       /* listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(CreditLidtActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = TempActivity.sqLiteHelper.getData("SELECT id FROM INVOICE");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                          //  showDialogUpdate(CreditLidtActivity.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = TempActivity.sqLiteHelper.getData("SELECT id FROM INVOICE");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                           // showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
      });
    }
}*/
/*
    ImageView imageViewCredit;

    private void showDialogUpdate(Activity activity, final int position) {

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.activity_credit_details);
        dialog.setTitle("Update");
/*
        imageViewCredit = (ImageView) dialog.findViewById(R.id.imageViewFood);
        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
        final EditText edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);
*/
  /*      // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        CreditLidtActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

      /*  btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity.sqLiteHelper.updateData(
                            edtName.getText().toString().trim(),
                            edtPrice.getText().toString().trim(),
                            MainActivity.imageViewToByte(imageViewFood),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateFoodList();
            }
        });*/
   /* }

    private void showDialogDelete(final int idCredit) {
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(CreditLidtActivity.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    TempActivity.sqLiteHelper.deleteData(idCredit);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
                updateFoodList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateFoodList() {
        // get all data from sqlite
        Cursor cursor = TempActivity.sqLiteHelper.getData("SELECT * FROM FOOD");
        list.clear();
       /* while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Food(name, price, image, id));
        }*/
      /*  adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 888) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 888 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewCredit.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}*/
