package com.example.kakeibo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.kakeibo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//ショッピングリストフラグメントとする

public class SyuusiFragment extends BaseFragment {

    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.editText2)
    EditText editText2;

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
        editText1.getText().toString();
        Integer.parseInt(String.valueOf(editText2));
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
