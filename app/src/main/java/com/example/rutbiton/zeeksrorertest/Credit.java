package com.example.rutbiton.zeeksrorertest;

import java.util.Date;

public class Credit extends Invoice{
    private String duedate;

    public Credit(String date, String store, String sum, String duedate, String category, byte[] image, int id) {
        super(date, store, sum, category, image, true, id);
        this.duedate = duedate;

    }

    public String getDuedate() {
        return duedate;
    }

    @Override
    public byte[] getImage() {
        return super.getImage();
    }
}