package com.example.sunsun1001.repfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import android.support.wearable.view.WearableListView;
import android.widget.Toast;
import android.hardware.*;
import android.content.Context;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class startPage extends Activity {


    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "S71tJtTHXhfxJS3Ak1cZDOeJd";
    private static final String TWITTER_SECRET = "1CfiDU7YavXeplHJOyW6oT2Atadf1W95O5MbocCoMKncCIdiv6";

    private TextView mTextView;
    // private Button mSelect;
    private WearableListView varList;
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_start_page);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            Log.v("v", "Inside MainAct: " + extras.getString("data"));
            String data = extras.getString("data");
            Intent myIntent = new Intent(startPage.this,
                    WatchStart.class);
            myIntent.putExtra("data", data);
            startActivity(myIntent);
        }

    }
}
