package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture;

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
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture.citystatelistviewmvvm.CityListActivity;

import java.util.ArrayList;

/**
 * Created by milan on 21.8.16.
 */
public class ArticleViewModel extends BaseObservable {
    private Article mArticle;
    private Context mContext;
    GetViewInterFace getViewInterFace;
    public ArticleViewModel(Article mArticle, Context mContext, GetViewInterFace getViewInterFaceR) {
        this.mArticle = mArticle;
        this.mContext = mContext;
        this.getViewInterFace = getViewInterFaceR;
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).centerCrop().into(view);
    }

    @Bindable
    public String getTitle() {
        return mArticle.getTitle();
    }

    public void setTitle(String title) {
        mArticle.setTitle(title);
        notifyPropertyChanged(BR.title);
    }
    public int getCardBackgroundColor() {
        return mArticle.isHighlight() ?
                ContextCompat.getColor(mContext, R.color.highlight) :
                Color.parseColor("#ffffffff");
    }
    public int getCommentsButtonVisibility() {
        return mArticle.getCommentsNumber() == 0 ?
                View.GONE : View.VISIBLE;
    }
    public int getCommentsNumber() {
        return mArticle.getCommentsNumber();
    }

    public String getExcerpt() {
        return mArticle.getExcerpt();
    }

    public String getImageUrl() {
        return mArticle.getImageUrl();
    }

    public void setRead(boolean read) {
        // change title of already read article:
        if (read && !mArticle.isRead()) {
            setTitle("READ: " + getTitle());
        }
        mArticle.setRead(read);
    }

    public  View.OnClickListener onCardClickevent(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(234);
               // getViewInterFace.SetViewData(integers);
                Toast.makeText(view.getContext(),mArticle.getImageUrl() , Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(view.getContext(), CityListActivity.class);
                 view.getContext().startActivity(intent);
            }
        };
    }

    public View.OnClickListener onReadMoreClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(view.getContext(), NextActivity.class);
                view.getContext().startActivity(intent);*/
                Toast.makeText(view.getContext(), "Opens article detail", Toast.LENGTH_SHORT).show();
                setRead(true);
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
