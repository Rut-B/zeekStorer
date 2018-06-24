package com.example.rutbiton.zeeksrorertest;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
* create class of search locartion by name of store,city,country,radius
* example of URL: https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=zara%20jerusalem%20israel&inputtype=textquery&fields=photos,formatted_address,name,rating,opening_hours,geometry&key=AIzaSyD1AhkL3xRtCejIj22i6A64AOVPG3_se-o
*
* next step get all store in radius of my location..
*
* for example:
* https://developers.google.com/places/web-service/search
* https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=mongolian%20grill&inputtype=textquery&fields=photos,formatted_address,name,opening_hours,rating&locationbias=circle:2000@47.6918452,-122.2226413&key=YOUR_API_KEY
* */
public class searchPlaces extends AsyncTask<String, Void, String> {

    private static final String LOG_TAG = "Google Places Autocomplete";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String TYPE_SEARCH = "/findplacefromtext";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyD1AhkL3xRtCejIj22i6A64AOVPG3_se-o";

    public static ArrayList searchPlacesByString(String input)  {
        ArrayList resultList = null;
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();

        try {

            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            System.out.println("============================================================");
            sb.append("?key=" + API_KEY);
            sb.append("&components=country:il");
            sb.append("&input=" + "zara");
            System.out.println("============================================================");
            URL url = new URL(sb.toString());
            System.out.println("================="+sb.toString()+"===========================================");
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

             //Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
    }

    @Override
    protected String doInBackground(String... strings) {
        searchPlacesByString("zara");
        return null;
    }

    //when the places changes this function is called , it responsible to send message if needed
    public void search(double longitude, double latitude) throws IOException {

        String country;
        String city;

        //get country name
        Geocoder geocoder = MainActivity.geocoder;
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude , 1);
        if (addresses != null && !addresses.isEmpty()) {
            country = addresses.get(0).getCountryName();
            city = addresses.get(0). getLocality();

            Log.d("country", "hiii"+country+"city = "+city);
//        try {
//            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_SEARCH + OUT_JSON);
//            sb.append("?key=" + API_KEY);
//            sb.append("&components=country:il");
//            sb.append("&input=" + "zara");
//            System.out.println("============================================================");
//            URL url = new URL(sb.toString());
//            System.out.println("================="+sb.toString()+"===========================================");
//            conn = (HttpURLConnection) url.openConnection();
//            InputStreamReader in = new InputStreamReader(conn.getInputStream());
//
//            //Load the results into a StringBuilder
//            int read;
//            char[] buff = new char[1024];
//            while ((read = in.read(buff)) != -1) {
//                jsonResults.append(buff, 0, read);
//            }
//        } catch (MalformedURLException e) {
//            Log.e(LOG_TAG, "Error processing Places API URL", e);
//            return resultList;
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "Error connecting to Places API", e);
//            return resultList;
//        } finally {
//            if (conn != null) {
//                conn.disconnect();
//            }
        }


    }
}
