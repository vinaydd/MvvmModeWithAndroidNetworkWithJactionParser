package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.utills.AppPreference;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.webManager.VolleySingleton;


/**
 * Created by root on 10/17/15.
 */
public class CommonBaseActivity extends AppCompatActivity {
    public AppPreference prefs;
    public boolean isErrorResponse;
    private static VolleySingleton mInstance;
    public static VolleySingleton volleyQueueInstance;
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new AppPreference(getApplicationContext());
        isErrorResponse = false;
        instantiateVolleyQueue();

    }
    public void instantiateVolleyQueue() {
        volleyQueueInstance = VolleySingleton.getInstance(getApplicationContext());
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(), new HurlStack());
        }
        return mRequestQueue;
    }
    public <T> void processResponse(T result) {
        isErrorResponse = false;
        if (result == null) {
            isErrorResponse = true;
            return;
        }

    }

    public void logout() {
        prefs.clearPreferences();
        Intent intent = new Intent(this, MainLaunchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }



}
