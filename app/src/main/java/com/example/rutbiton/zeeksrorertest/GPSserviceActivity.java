package com.example.rutbiton.zeeksrorertest;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/*
 * this class responsible to GPS service [Rut]
 *
 * */
public class GPSserviceActivity extends Service {

    private LocationListener listener;
    private LocationManager locationManager=null;
    private static final String LOG_TAG = "Google Places Autocomplete";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String TYPE_NEARBY_SEARCH = "/nearbysearch";
    private static final String TYPE_SEARCH = "/findplacefromtext";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyD1AhkL3xRtCejIj22i6A64AOVPG3_se-o";
    private static final int RADIUS = 50000;
    private static final String STRING_STATUS = "\"status\" : \" OK \"" ;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10f;
    private List<String> placesList;
    private String creditsListUp = "";
    private String name_store = "zara";
    private String NOTIFI_TITLE = "you can use a credits of:\n";
    public int counter=0;
public static SQLiteHelper sqLiteHelper;

    zeekNotification zeek_notification;
    ArrayList resultList = null;
    HttpURLConnection conn = null;
    StringBuilder jsonResults = new StringBuilder();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    //this function is called one time when the application is install on phone..
    public void onCreate()
    {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        zeek_notification = new zeekNotification(getApplicationContext());
        // placesList = getCreditListPlaces();
//        System.out.println("\nonCreate :onLocationChanged============================================================\n***\n");
        Log.d("ddd","onCreate :onLocationChanged============================================================");
        sqLiteHelper = new SQLiteHelper(this, "InvoiceDB.sqlite", null, 1);
        listener = new LocationListener()
        {
            @Override

            public void onLocationChanged(Location location)
            {
                // zeek_notification.sendNotification(NOTIFI_TITLE,"hahahahaha",33);
//                System.out.println("\nonCreate :onLocationChanged============================================================\n***\n");
                Log.d("ddd","\nonCreate :onLocationChanged============================================================\n***\n");
               /* try
                {

                    // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=31.771959,%2035.217018&radius=50000&name=zara&key=AIzaSyD1AhkL3xRtCejIj22i6A64AOVPG3_se-o
                    StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_NEARBY_SEARCH + OUT_JSON);
                    sb.append("?location=" + location.getLatitude() + "," + location.getLongitude());
                    sb.append("&radius=" + RADIUS);
                    sb.append("&name=" + name_store);
                    sb.append("&key=" + API_KEY);
                    URL url = new URL(sb.toString());
                    System.out.println("onCreate :============================================================");
                    conn = (HttpURLConnection) url.openConnection();

                    if(conn==null)
                    {
                        System.out.println("\nconnection failes :( conn = null\n");
                        return;
                    }

                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    if(in==null)
                    {
                        System.out.println("\nconnection failes :( in = null\n");
                        return;
                    }
                    int read;
                    char[] buff = new char[10000];

                    while ((read = in.read(buff)) != -1)
                    {
                        jsonResults.append(buff, 0, read);
                    }

                    try
                    {
                        String json_string =jsonResults.toString();
                        JSONObject jsonObj = new JSONObject(json_string);
                        int count = jsonObj.length();

                        if(json_string.contains("ZERO_RESULT") && (count>0))
                        {
                            System.out.println("\n__________ZERO_RESULT\n");

                        }
                        else
                        {

                            System.out.println("\n_________get_address____________________\n");

                        }

                    } catch (JSONException e)
                    {
                        Log.d("debug", "onCreate :Cannot process JSON results");
                    }
                } catch (MalformedURLException e)
                {
                    Log.d("debug", "Error processing Places API URL");
                } catch (IOException e)
                {
                    Log.d("debug", "Error connecting to Places API");
                }
                finally
                {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }*/
            }


            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 500, listener);

    }


    //this function is called in every change of location..
    private Timer timer;
    private TimerTask timerTask;
    long oldTime=0;
    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, to wake up every 1 second
        timer.schedule(timerTask, 10000, 10000); //
    }

    /**
     * it sets the timer to print the counter every x seconds
     */
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run()
            {
                placesList = getCreditListPlaces();
                  zeek_notification.sendNotification("zara","credit"+placesList.size()+ placesList.get(0),33);

                Log.i("in timer", "in timer ++++  "+ (counter++));

            }
        };
    }
    public int onStartCommand(Intent intent, int flags, int startId)
    {

        startTimer();
        //  zeek_notification.sendNotification("zara","credit",33);
        listener = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                zeek_notification.sendNotification(NOTIFI_TITLE,"777777777777777777777777",33);

                placesList = getCreditListPlaces();
                creditsListUp="";
                boolean flag= false;
                for (int i = 0; i < placesList.size(); i++) {
                    String name = placesList.get(i);
                    flag = udateCreditNotifi(name_store,location);
                    if(flag)
                    {
                        creditsListUp += name+" ,\n";
                    }

                }
                if (!creditsListUp.equals(""))
                {
                    zeek_notification.sendNotification(NOTIFI_TITLE,creditsListUp,33);
                }

            }


            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return super.onStartCommand(intent, flags, startId);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 500, listener);

        return START_STICKY;

    }

    private boolean udateCreditNotifi(String name_store,Location location)
    {
        try
        {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_NEARBY_SEARCH + OUT_JSON);
            sb.append("?location=" + location.getLatitude() + "," + location.getLongitude());
            sb.append("&radius=" + RADIUS);
            sb.append("&name=" + name_store);
            sb.append("&key=" + API_KEY);
            URL url = new URL(sb.toString());
            Log.d("ddd","onStartCommand :=================\n" + sb.toString() + "\n===========================================");
            conn = (HttpURLConnection) url.openConnection();

            if(conn==null)
            {
                System.out.println("\nonStartCommand : connection failes [conn = null]\n");
                return false;
            }

            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            if(in==null)
            {
                System.out.println("\nonStartCommand :InputStreamReader failes [in = null]\n");
                return false;
            }
            int read;
            char[] buff = new char[10000];

            while ((read = in.read(buff)) != -1)
            {
                jsonResults.append(buff, 0, read);
            }

            try
            {
                String json_string =jsonResults.toString();
                JSONObject jsonObj = new JSONObject(json_string);
                Log.d("ddd","\n-------------------------------\n"+json_string);
                int count = jsonObj.length();

                if(json_string.contains("ZERO_RESULT"))
                {
                    Log.d("ddd","\n__________ZERO_RESULT\n"+STRING_STATUS);

                    return true;
                }
                else
                {
                    Log.d("ddd","\n_________get_address____________________\n"+STRING_STATUS);

                    return true;

                }

            } catch (JSONException e)
            {
                Log.d("debug", "onCreate :Cannot process JSON results");
            }
        } catch (MalformedURLException e)
        {
            Log.d("debug", "Error processing Places API URL");
        } catch (IOException e)
        {
            Log.d("debug", "Error connecting to Places API");
        }
        finally
        {
            if (conn != null)
            {
                conn.disconnect();
            }
        }
        return  false;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager != null){
            //noinspection MissingPermission
            locationManager.removeUpdates(listener);
        }
        Intent broadcastIntent = new Intent("ActivityRecognition.RestartSensor");
        sendBroadcast(broadcastIntent);
    }
    public static ArrayList<String> getCreditListPlaces(){
        //System.out.println("\n__________________    in func    __________\n");
        ArrayList<String> stores = new ArrayList<>();
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM INVOICE WHERE isCredit='true'");
        while (cursor.moveToNext()) {

            String store = cursor.getString(1);
            stores.add(store);
            System.out.println("\n____________________________\n"+store);
        }


        return stores;
    }

}
