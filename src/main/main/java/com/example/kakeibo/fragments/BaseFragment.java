package com.example.kakeibo.fragments;

import android.support.v4.app.Fragment;

import com.example.kakeibo.activities.BaseActivity;

public class BaseFragment extends Fragment {

    public BaseActivity obtainBaseActivity(){
        return (BaseActivity)getActivity();
    }
}
