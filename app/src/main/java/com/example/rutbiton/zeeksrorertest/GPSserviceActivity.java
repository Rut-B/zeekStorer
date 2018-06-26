package com.example.rutbiton.zeeksrorertest;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

/*
 * this class responsible to GPS service [Rut]
 *
 * */
public class GPSserviceActivity extends Service {

    private LocationListener listener;
    private LocationManager locationManager;
    private static final String LOG_TAG = "Google Places Autocomplete";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String TYPE_NEARBY_SEARCH = "/nearbysearch";
    private static final String TYPE_SEARCH = "/findplacefromtext";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyD1AhkL3xRtCejIj22i6A64AOVPG3_se-o";
    private static final int RADIUS = 5000;
    ArrayList resultList = null;
    HttpURLConnection conn = null;
    StringBuilder jsonResults = new StringBuilder();
    private String name_store = "zara";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    //this function is called one time when the application is install on phone..
    public void onCreate() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

                notificatio_func();
                try {
                    // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=31.771959,%2035.217018&radius=50000&name=zara&key=AIzaSyD1AhkL3xRtCejIj22i6A64AOVPG3_se-o
                    StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_NEARBY_SEARCH + OUT_JSON);
                    sb.append("?location=" + location.getLatitude() + "," + location.getLongitude());
                    sb.append("&radius=" + RADIUS);
                    sb.append("&name=" + name_store);
                    sb.append("&key=" + API_KEY);
                    URL url = new URL(sb.toString());
                    System.out.println("onCreate :=================\n" + sb.toString() + "\n===========================================");
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
                        System.out.println("\njessonnnnnnnn\n"+ jsonResults.toString());
                        // Create a JSON object hierarchy from the results
                        JSONObject jsonObj = new JSONObject(jsonResults.toString());
                        int count = jsonObj.length();
                        System.out.println("\n______countcountcountcountcountcount______"+count);
                        String json_string =jsonResults.toString();
                        if(json_string.contains("ZERO_RESULT"))
                        {
                            System.out.println("\n__________jsonObj.ZERO_RESULTSvZERO_RESULTSZERO_RESULTSZERO_RESULTS()____________________\n");

                        }
                        else
                        {
                            //String get_address = jsonObj .getString("ZERO_RESULTS");
                            System.out.println("\n_________get_address____________________\n");

                        }

//                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(),"1")
//                                .setDefaults(Notification.DEFAULT_ALL)
//                                .setSmallIcon(R.mipmap.ic_launcher)
//                                .setContentTitle("My notification")
//                                .setContentText("Much longer text that cannot fit one line...");
//
//
//
//
//
//                        NotificationManager mNotificationManager =  (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                        mNotificationManager.notify(1, mBuilder.build());




                        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), "M_CH_ID");

                        notificationBuilder.setAutoCancel(true)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setTicker("Hearty365")
                                .setPriority(Notification.PRIORITY_MAX) // this is deprecated in API 26 but you can still use for below 26. check below update for 26 API
                                .setContentTitle("Default notification")
                                .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                                .setContentInfo("Info");

                        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(1, notificationBuilder.build());






//                        NotificationManager notificationManager =
//                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                        int notifyId = 1;
//                        //String channelId = "some_channel_id";
//                        Notification notification = new Notification.Builder(getApplicationContext())
//                                .setContentTitle("Some Message")
//                                .setContentText("You've received new messages!")
//                                .setSmallIcon(R.mipmap.ic_launcher)
//                                //.setChannel(channelId)
//                                .build();
//
//                        notificationManager.notify(notifyId, notification);
                        //JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");
                        // Extract the Place descriptions from the results
//                            resultList = new ArrayList(predsJsonArray.length());
//                            for (int i = 0; i < predsJsonArray.length(); i++) {
//                                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
//                                System.out.println("============================================================");
//                                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
//                            }
                    } catch (JSONException e) {
                        Log.d("ddd", "onCreate :Cannot process JSON results");
                    }


                } catch (MalformedURLException e) {
                    Log.d("ddd", "Error processing Places API URL");
                } catch (IOException e) {
                    Log.d("ddd", "Error connecting to Places API");
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }

            private void notificatio_func() {


                Log.d("not","**************************************notification");
                NotificationCompat.Builder builder;
                NotificationManager notificationManager;
                int notification_id;
                RemoteViews remoteViews;
                Context context;

                context = getApplicationContext();
                notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                builder = new NotificationCompat.Builder(context,"1");

                remoteViews = new RemoteViews(getPackageName(),R.layout.custom_notification);
                remoteViews.setImageViewResource(R.id.notif_icon,R.mipmap.ic_launcher);
                remoteViews.setTextViewText(R.id.notif_title,"TEXT");
                remoteViews.setProgressBar(R.id.progressBar,100,3,true);




                notification_id = (int) System.currentTimeMillis();

                Intent button_intent = new Intent("button_click");
                button_intent.putExtra("id",notification_id);
                PendingIntent button_pending_event = PendingIntent.getBroadcast(context,notification_id,
                        button_intent,0);

                remoteViews.setOnClickPendingIntent(R.id.button,button_pending_event);

                Intent notification_intent = new Intent(context,MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notification_intent,0);

                builder.setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .setCustomBigContentView(remoteViews)
                        .setContentIntent(pendingIntent);

                notificationManager.notify(notification_id,builder.build());
                Log.d("not","*********************enddddddddddddddddddddddd**notification");

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

    @Override
    //this function is called in every change of location..
    public int onStartCommand(Intent intent, int flags, int startId) {

        listener = new LocationListener() {


            @Override
            public void onLocationChanged(Location location) {



//_____________________________________________________________________________________________________________
                try {
                    // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=31.771959,%2035.217018&radius=50000&name=zara&key=AIzaSyD1AhkL3xRtCejIj22i6A64AOVPG3_se-o
                    StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_NEARBY_SEARCH + OUT_JSON);
                    sb.append("?location=" + location.getLatitude() + "," + location.getLongitude());
                    sb.append("&radius=" + RADIUS);
                    sb.append("&name=" + name_store);
                    sb.append("&key=" + API_KEY);
                    URL url = new URL(sb.toString());
                    System.out.println("onStartCommand:=================" + sb.toString() + "===========================================");
                    conn = (HttpURLConnection) url.openConnection();

                    if(conn!=null)
                    {
                        System.out.println("onStartCommand:=================%%%%%%%%%%%%%%%%%%%%===========================================");
                        InputStreamReader in = new InputStreamReader(conn.getInputStream());

                        int read;
                        char[] buff = new char[1024];
                        while ((read = in.read(buff)) != -1) {
                            jsonResults.append(buff, 0, read);
                        }
//
//                        try {
//                            // Create a JSON object hierarchy from the results
//                            JSONObject jsonObj = new JSONObject(jsonResults.toString());
//                            System.out.println("onStartCommand:=================%%%%%%%%%%%%%%%%%%%%===========================================");
//                            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");
//
//                            // Extract the Place descriptions from the results
//                            resultList = new ArrayList(predsJsonArray.length());
//                            for (int i = 0; i < predsJsonArray.length(); i++) {
//                                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
//                                System.out.println("============================================================");
//                                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
//                            }
//                        } catch (JSONException e) {
//                            Log.d("hggff", "onStartCommand :Cannot process JSON results");
//                        }

                    }


//                int read;
//                char[] buff = new char[1024];
//                while ((read = in.read(buff)) != -1) {
//
//                    jsonResults.append(buff, 0, read);
//                }


                } catch (MalformedURLException e) {

                    Log.d("ddd", "Error processing Places API URL");
                    // return resultList;
                } catch (IOException e) {
                    Log.d("ddd", "Error connecting to Places API");
                    //return resultList;
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }



















//                try {
//                    MainActivity.searcher.search(location.getLongitude(),location.getLatitude());
//                    Toast.makeText(GPSserviceActivity.this, "searcher searcher searcher",Toast.LENGTH_SHORT).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                Toast.makeText(GPSserviceActivity.this, "server running onLocationChanged",Toast.LENGTH_SHORT).show();
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
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return super.onStartCommand(intent, flags, startId);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 500, listener);

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager != null){
            //noinspection MissingPermission
            locationManager.removeUpdates(listener);
        }
    }
}
