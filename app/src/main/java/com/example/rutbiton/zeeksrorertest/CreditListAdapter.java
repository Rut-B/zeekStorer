package com.example.rutbiton.zeeksrorertest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class CreditListAdapter extends ArrayAdapter<Invoice>{

    private Context context;
   // private  int layout;
    private ArrayList<Invoice> invoiceList;
    private int lastPosition = -1;

    private class ViewHolder{
        ImageView imageView;
        TextView txtStore, txtSum, txtDate, category;
    }

    public CreditListAdapter(ArrayList<Invoice> invoiceList, Context context) {
        super(context, R.layout.activity_credit_item, invoiceList);
        this.context = context;
        this.invoiceList = invoiceList;
    }

    /*@Override
    public int getCount() {
        return invoiceList.size();
    }*/
/*
    @Override
    public Object getItem(int position) {
        return invoiceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
*/

/*
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtStore = (TextView) row.findViewById(R.id.txtStore);
            holder.txtSum = (TextView) row.findViewById(R.id.txtSum);
            holder.txtDate = (TextView) row.findViewById(R.id.txtDate);
            holder.imageView = (ImageView) row.findViewById(R.id.imgDoc);
            row.setTag(holder);
        }
        else {

            holder = (ViewHolder) row.getTag();
        }

        Invoice invoice = invoiceList.get(position);

        holder.txtStore.setText(invoice.getStore());
        holder.txtSum.setText(invoice.getSum());
        holder.txtDate.setText(invoice.getDate());

        byte[] invoiceImage = invoice.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(invoiceImage, 0, invoiceImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Invoice dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_credit_update, parent, false);
            viewHolder.txtStore = (TextView) convertView.findViewById(R.id.txtStore);
            viewHolder.txtSum = (TextView) convertView.findViewById(R.id.txtSum);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
          //  viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgDoc);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtStore.setText(dataModel.getStore());
        viewHolder.txtSum.setText(dataModel.getSum());
        viewHolder.txtDate.setText(dataModel.getDate());
      //  viewHolder.imageView.setOnClickListener(this);
       // viewHolder.imageView.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}