package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.R;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.model.CountryStateCityListModel;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture.MainActivity;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.responce.BaseResponse;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.responce.CountryStateCityListResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainLaunchActivity extends CommonBaseActivity {
    ArrayList<CountryStateCityListModel>  list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainl);
        AndroidNetworking.initialize(getApplicationContext());


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("vehicleNumber","UP200002");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // ManagerClassOfVolley.getInstance().getLogin(this, Constants.JSON_OBJECTS,Constants.GET_REQUEST,null ,true);

        try {
            ObjectMapper mapper  = new ObjectMapper();

            String values = getCountoryStateCityList();
            CountryStateCityListResponse rsp = mapper.readValue(values, CountryStateCityListResponse.class);
            CountryStateCityListModel data [] = rsp.getData();
              list = new ArrayList<>(Arrays.asList(data));
        } catch (IOException e) {
            e.printStackTrace();
        }


    //    FOR IMAGE DOWNLOAD LIBRARY OF ANDROID NETWORK PARSER

     /*  <com.androidnetworking.widget.ANImageView
        android:id="@+id/imageView"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_gravity="center" />
        final ANImageView anImageView = (ANImageView) findViewById(R.id.imageView);
        anImageView.setDefaultImageResId(R.drawable.driver);
        anImageView.setErrorImageResId(R.drawable.driver);
        anImageView.setImageUrl("https://api.androidhive.info/images/glide/medium/deadpool.jpg");

    */

      findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLaunchActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
    @Override
    public <T> void processResponse(T result) {
        super.processResponse(result);
        BaseResponse baseResponse  = (BaseResponse)result;
        if (result instanceof BaseResponse){
            BaseResponse resp = (BaseResponse) result;
        }
    }


    private String getCountoryStateCityList() {
        String json = null;
        try {
            String path = "android.resource://" + getPackageName() + "/" + R.raw.jsonlist;
            //  path = "android.resource://" + getPackageName() + "/" + R.raw.gcclist;
            InputStream is = getResources().openRawResource(R.raw.jsonlist);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;


    }



}
