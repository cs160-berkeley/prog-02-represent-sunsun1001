package com.example.sunsun1001.repfinder;

//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.View;
//import android.app.Activity;
//import android.location.*;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationListener;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Locale;
//
//
//public class LatLong extends AppCompatActivity implements
//        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
//
//
//    LocationRequest mLocationRequest;
//    GoogleApiClient mGoogleApiClient;
//    public double mLat;
//    public double mLong;
//    String zipcode = "";
//
//    Geocoder gcoder=new Geocoder(this, Locale.ENGLISH);
//    List<Address> someData = null;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.a);
//        buildGoogleApiClient();
//        zipcode = getIntent().getStringExtra("inputCode");
//        Log.v("v", "Zipcode in LatLong: " + zipcode);
//        mGoogleApiClient.connect();
//
//
//    }
//
//    protected synchronized void buildGoogleApiClient() {
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//    }
//
//    @Override
//    public void onConnected(Bundle bundle) {
//        if (ActivityCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
//                mGoogleApiClient);
//        if (mLastLocation != null) {
//            mLat = (mLastLocation.getLatitude());
//            mLong = (mLastLocation.getLongitude());
//        }
//
//        else {
//
//            try {
//                someData = gcoder.getFromLocationName(zipcode, 1);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            Address address = someData.get(0);
//            if (someData.size() > 0) {
//                mLat = address.getLatitude();
//                mLong = address.getLongitude();
//                Log.v("v", "Lat in LatLong from Zip: " + mLat);
//
//            }
//        }
//
//        mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(10000); //10 seconds
//        mLocationRequest.setFastestInterval(5000); //5 seconds
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//        mLocationRequest.setSmallestDisplacement(1); //1 meter
//
//        Intent myIntent = new Intent(LatLong.this, listReps.class);
//        //myIntent.putExtra("latZip", Double.toString(latitude));
//        //myIntent.putExtra("longZip", Double.toString(longitude));
//        myIntent.putExtra("mLat", Double.toString(mLat));
//        myIntent.putExtra("mLong", Double.toString(mLong));
//        startActivity(myIntent);
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//        Toast.makeText(this,"onConnectionSuspended",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult) {
//        Toast.makeText(this,"onConnectionFailed",Toast.LENGTH_SHORT).show();
//    }
//}