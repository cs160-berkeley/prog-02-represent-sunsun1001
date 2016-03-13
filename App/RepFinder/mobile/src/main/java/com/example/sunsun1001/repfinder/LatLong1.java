package com.example.sunsun1001.repfinder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.location.*;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class LatLong1 extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    public double latZip;
    public double longZip;
    public double mLat;
    public double mLong;
    String zipcode = "";
    List<Address> someData = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        buildGoogleApiClient();
        zipcode = getIntent().getStringExtra("inputCode");
       // Log.v("v", "Zipcode in LatLong: " + zipcode);
        mGoogleApiClient.connect();
        Geocoder gcoder = new Geocoder(this, Locale.ENGLISH);

        if (zipcode != null) {

            try {
                someData = gcoder.getFromLocationName(zipcode, 1);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Address address = someData.get(0);
            if (someData.size() > 0) {
                latZip = address.getLatitude();
                longZip = address.getLongitude();
               // Log.v("v", "Lat in LatLong from Zip: " + latZip);
               // Log.v("v", "Long in LatLong from Zip: " + longZip);

            }
        }



    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null && zipcode == null) {
            mLat = (mLastLocation.getLatitude());
            mLong = (mLastLocation.getLongitude());
            //Log.v("v", "mLong in LatLong from Zip: " + mLong);

        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000); //10 seconds
        mLocationRequest.setFastestInterval(5000); //5 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setSmallestDisplacement(1); //1 meter

        Intent myIntent = new Intent(LatLong1.this, listReps.class);
        myIntent.putExtra("latZip", Double.toString(latZip));
        myIntent.putExtra("longZip", Double.toString(longZip));
        myIntent.putExtra("mLat", Double.toString(mLat));
        myIntent.putExtra("mLong", Double.toString(mLong));
        startActivity(myIntent);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,"onConnectionSuspended",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this,"onConnectionFailed",Toast.LENGTH_SHORT).show();
    }


}