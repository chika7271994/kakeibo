package com.example.kakeibo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kakeibo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

//ショッピングリストフラグメントとする

public class SyuusiFragment extends BaseFragment {

    public static SyuusiFragment newInstance() {
        return new SyuusiFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.syuusi_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.syuusi_input)
    void onAddClick() {
        navigateToFragment(InputFragment.newInstance());
    }
}


    /*@OnClick(R.id.syuusi_input)
    void btnSyuusiClick(){
        final Database activity = obtainDatabase();
        if (activity == null){
            return;
        }*/

        //KakeiboFragmentを表示
        //activity.replaceFragment(KakeiboFragment.newInstance());
    //}

