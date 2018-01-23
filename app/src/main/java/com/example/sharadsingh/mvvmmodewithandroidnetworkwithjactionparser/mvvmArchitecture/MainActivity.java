package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import com.androidnetworking.AndroidNetworking;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.R;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.databinding.MainActivityBinding;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.model.CountryStateCityListModel;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.responce.CountryStateCityListResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GetViewInterFace {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(getApplicationContext());
       // AndroidNetworking.initialize(getApplicationContext());

        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.contactList.setLayoutManager(layoutManager);



        List<Article> articles = new ArrayList<>();
        try {
            ObjectMapper mapper  = new ObjectMapper();

            String values = getCountoryStateCityList();
            CountryStateCityListResponse rsp = mapper.readValue(values, CountryStateCityListResponse.class);
            CountryStateCityListModel data [] = rsp.getData();
            ArrayList<CountryStateCityListModel>  list = new ArrayList<>(Arrays.asList(data));
           // articles.addAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

       articles.add(new Article("An outbreak of parasitic bees",
                "This summer, we are facing a very serious issue. And it is nothing else but an outbreak of parasitic bees.",
                true, "android.resource://com.example.databindingblog/drawable/bee", 45));
        articles.add(new Article("Brno - the city of 2016",
                "It has been announced by the committee of know-it-all that Brno has been elected city of year 2016.",
                false, "android.resource://com.example.databindingblog/drawable/brno", 0));
        articles.add(new Article("Restaurants in trouble",
                "Restaurants offering daily menus could soon face a serious trouble. The government has just...",
                true, "android.resource://com.example.databindingblog/drawable/food", 1));
        articles.add(new Article("Survey amongst drivers reveals shocking facts",
                "A survey taken by 1100 drivers commuting every day to work shows that the drivers mostly drive their car alone.",
                false, "android.resource://com.example.databindingblog/drawable/driver", 33));
        articles.add(new Article("Rugby for everyone?",
                "Until lately, rugby has been considered a sport played only by men. What are the consequences...",
                false, "android.resource://com.example.databindingblog/drawable/rugby", 11));


        ArticleAdapter adapter = new ArticleAdapter(articles, this ,this);
        binding.contactList.setAdapter(adapter);


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

    @Override
    public void getDataView(ArrayList values) {
        Toast.makeText(this, "" +values.get(0), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void SetViewData(ArrayList values) {
        Toast.makeText(this, "" +values.get(0), Toast.LENGTH_SHORT).show();

    }
}
