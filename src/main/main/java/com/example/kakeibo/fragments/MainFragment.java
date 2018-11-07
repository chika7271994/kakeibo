package com.example.kakeibo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.kakeibo.CalendarAdapter;
import com.example.kakeibo.Kakeibo;
import com.example.kakeibo.R;
import com.example.kakeibo.activities.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends BaseFragment {

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState){
                View view = inflater.inflate(R.layout.memo_index, container, false);
                ButterKnife.bind(this, view);
                return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.syuusi)
    void btnSyuusi(){
        final BaseActivity activity = obtainBaseActivity();
        if (activity == null){
            return;
        }

        activity.replaceFragment(Kakeibo.newInstance());
    }
}
