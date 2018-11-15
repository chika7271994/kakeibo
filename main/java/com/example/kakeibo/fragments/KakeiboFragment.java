package com.example.kakeibo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kakeibo.R;
import com.example.kakeibo.activities.CalendarAdapter;
import com.example.kakeibo.activities.KakeiboListActivity;
import com.example.kakeibo.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

//Mainフラグメントとする

public class KakeiboFragment extends BaseFragment {

    public static KakeiboFragment newInstance() {
        return new KakeiboFragment();
    }

    private String day;

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
        //値を受け取る
        //dataの中身がnullになる
        Bundle bundle = getArguments();
        if (bundle != null){
            day = bundle.getString("data");
        }else {
            LogUtil.debug("onCreate:KakeiboFragment", "dataの中身は" + day);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.syuusi)
    void btnSyuusiClick(){
        navigateToFragment(SyuusiFragment.newInstance());
    }
}