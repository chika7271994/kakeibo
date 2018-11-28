package com.example.kakeibo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kakeibo.R;
import com.example.kakeibo.activities.KakeiboListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

//Mainフラグメントとする

public class KakeiboFragment extends BaseFragment {

    public static KakeiboFragment newInstance() {
        return new KakeiboFragment();
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
        final KakeiboListActivity activity = obtainKakeiboListActivity();
        if (activity == null){
            return;
        }
        //MemoFragmentを表示
        activity.replaceFragment(SyuusiFragment.newInstance());
    }
}