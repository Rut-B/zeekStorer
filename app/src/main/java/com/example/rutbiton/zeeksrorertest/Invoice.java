package com.example.rutbiton.zeeksrorertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

public class Invoice {
    private int id;
    private String date;
    private String store;
    private String sum;
    //private String imagePath;
    private String category;
    private byte [] image;
    private boolean isCredit;

    public Invoice(String date, String store, String sum, String category, byte [] image, boolean isCredit, int id) {
        this.date = date;
        this.store = store;
        this.sum = sum;
        this.image = image;
        this.category = category;
        this.isCredit = isCredit;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public byte[] getImage() {
        return image;
    }

    public String getSum() {
        return sum;
    }

    public String getDate() {
        return date;
    }

    public String getStore() {
        return store;
    }

    public boolean isCredit() {
        return isCredit;
    }
}
