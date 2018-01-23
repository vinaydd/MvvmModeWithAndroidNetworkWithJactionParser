package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture.citystatelistviewmvvm;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.R;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.activity.CommonBaseActivity;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.databinding.CitylistActivityBinding;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.model.CountryStateCityListModel;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture.Article;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture.GetViewInterFace;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.responce.CountryStateCityListResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sharadsingh on 22/01/18.
 */

public class CityListActivity extends CommonBaseActivity {

    List<CityListModel> articles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(getApplicationContext());
        CitylistActivityBinding citylistActivityBinding = DataBindingUtil.setContentView(this, R.layout.citylist_activity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        citylistActivityBinding.cityList.setLayoutManager(layoutManager);
        articles = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String values = getCountoryStateCityList();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            CityListResponse rsp = mapper.readValue(values, CityListResponse.class);
           // CityListResponse rsp = new Gson().fromJson(values, CityListResponse.class);
            CityListModel data[] = rsp.getData();
            ArrayList<CityListModel> list = new ArrayList<>(Arrays.asList(data));
            articles.addAll(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CityListAdapter cityListAdapter = new CityListAdapter(articles, this);
        citylistActivityBinding.cityList.setAdapter(cityListAdapter);
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
