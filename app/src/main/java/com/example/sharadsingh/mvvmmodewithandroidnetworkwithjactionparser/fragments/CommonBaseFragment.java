package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.utills.AppPreference;


/**
 * Created by ios4_dev on 11/19/15.
 */
public class CommonBaseFragment extends Fragment {

    public AppPreference prefs;
    public boolean isErrorResponse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new AppPreference(getActivity().getApplicationContext());
        isErrorResponse = false;
    }
    public void nestedReplaceFragment(int container, Fragment newFragment, boolean backStackTag, String fragmentTag) {
        try {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            if (getChildFragmentManager().getBackStackEntryCount() == 0) {
                transaction.add(container, newFragment, fragmentTag);
            } else {
                transaction.replace(container, newFragment, fragmentTag);
            }
            if (backStackTag) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
            getChildFragmentManager().executePendingTransactions();
        } catch (Exception e) {
            Log.e("exception ", " on adding fragment");
            // TODO: handle exception
        }
    }

    public boolean popFragment() {
        Log.e("test", "pop fragment: " + getChildFragmentManager().getBackStackEntryCount());
        boolean isPop = false;
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            isPop = true;
            getChildFragmentManager().popBackStack();
        }
        return isPop;
    }

    public void clearStack() {
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            getChildFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }


    public <T> void processFragmentResponse(T result) {
        isErrorResponse = false;
        if (result == null) {
            try {
                isErrorResponse = true;
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();

    }
}