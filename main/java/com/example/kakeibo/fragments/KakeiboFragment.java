package com.example.kakeibo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kakeibo.R;
import com.example.kakeibo.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

//Mainフラグメントとする

public class KakeiboFragment extends BaseFragment {

    public static KakeiboFragment newInstance(String currentDate) {
        KakeiboFragment fragment = new KakeiboFragment();
        //値を受け取る
        Bundle bundle = new Bundle();
        bundle.putString("data", currentDate);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.memo_index, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.syuusi)
    void btnSyuusiClick(){
        Bundle bundle = getArguments();
        String day = bundle.getString("data");
        LogUtil.debug("KakeiboFragment", "日付は"+ day);
        navigateToFragment(SyuusiFragment.newInstance(day));
    }
}