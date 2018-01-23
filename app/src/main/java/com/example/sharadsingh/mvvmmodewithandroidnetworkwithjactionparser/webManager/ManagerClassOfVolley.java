package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.webManager;

import android.content.Context;


import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.responce.BaseResponse;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.utills.Constants;

import org.json.JSONObject;

/**
 * Created by root on 10/11/16.
 */
public class ManagerClassOfVolley {
    private static ManagerClassOfVolley singleInstance;
    protected ManagerClassOfVolley() {
    }
    public static ManagerClassOfVolley getInstance() {
        if (singleInstance == null) {
            singleInstance = new ManagerClassOfVolley();
        }
        return singleInstance;
    }
    public  void  getLogin(Context context, String requstType, String Mode, JSONObject obj , boolean isProgress){
        CommonWebCallManagerClass<BaseResponse> httptask = new CommonWebCallManagerClass<>(context, Constants.NEW_HOST_URL, BaseResponse.class, Constants.STATE_KEY,requstType,Mode,obj);
        httptask.setIsApiKeyRequired(true);
        httptask.setForFragment(false);
        httptask.setShowProgress(isProgress);
    }

}
