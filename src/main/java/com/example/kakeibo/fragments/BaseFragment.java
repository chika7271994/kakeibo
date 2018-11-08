package com.example.kakeibo.fragments;

import android.support.v4.app.Fragment;

import com.example.kakeibo.activities.KakeiboListActivity;

public class BaseFragment extends Fragment {

    public KakeiboListActivity obtainKakeiboListActivity(){
        return (KakeiboListActivity) getActivity();
    }
}
