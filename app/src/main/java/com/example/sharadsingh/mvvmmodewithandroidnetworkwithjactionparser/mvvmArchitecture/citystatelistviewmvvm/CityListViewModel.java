package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture.citystatelistviewmvvm;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.BR;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.R;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.activity.MainLaunchActivity;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture.Article;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture.GetViewInterFace;

import java.util.ArrayList;

/**
 * Created by milan on 21.8.16.
 */
public class CityListViewModel extends BaseObservable {
    private CityListModel mArticle;
    private Context mContext;

    public CityListViewModel(CityListModel mArticle, Context mContext) {
        this.mArticle = mArticle;
        this.mContext = mContext;

    }
    @Bindable
    public String getCityName() {
        return mArticle.getCityName();
    }

    public void setCityName(String title) {
        mArticle.setCityName(title);
        notifyPropertyChanged(BR.title);
    }


    @Bindable
    public String getCityID() {
        return mArticle.getCityId();
    }

    public void setCityID(String title) {
        mArticle.setCityId(title);
        notifyPropertyChanged(BR.cityID);
    }



    @Bindable
    public String getStateName() {
        return mArticle.getStateName();
    }

    public void setStateName(String title) {
        mArticle.setStateName(title);
        notifyPropertyChanged(BR.stateName);
    }


    @Bindable
    public String getStateID() {
        return mArticle.getStateId();
    }

    public void setStateID(String title) {
        mArticle.setStateId(title);
        notifyPropertyChanged(BR.stateID);
    }


    @Bindable
    public String getCountryName() {
        return mArticle.getCountryName();
    }

    public void setCountryName(String title) {
        mArticle.setCountryName(title);
        notifyPropertyChanged(BR.countryName);
    }


    @Bindable
    public String getCountryID() {
        return mArticle.getCountryId();
    }

    public void setCountryID(String title) {
        mArticle.setCountryId(title);
        notifyPropertyChanged(BR.countryID);
    }

    public  View.OnClickListener okButtonClick() {


      return   new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "ok", Toast.LENGTH_SHORT).show();

            }
        };
    }


    public  View.OnClickListener CancelButtonClick(){

        return   new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Cancel", Toast.LENGTH_SHORT).show();

            }
        };
    }


    public int getCommentsButtonVisibility() {
        return mArticle.getCountryId() != "101" ?
                View.GONE : View.VISIBLE;
    }

    public  View.OnClickListener onCardClickevent(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),mArticle.getCityId() , Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(view.getContext(), MainLaunchActivity.class);
                 view.getContext().startActivity(intent);
            }
        };
    }

    public View.OnClickListener onReadMoreClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Opens article detail", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public View.OnClickListener onCommentsClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Opens comments detail", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
