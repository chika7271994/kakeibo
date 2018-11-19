package com.example.kakeibo.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kakeibo.R;
import com.example.kakeibo.database.Database;
import com.example.kakeibo.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//Mainフラグメントとする

public class KakeiboFragment extends BaseFragment {

    public static KakeiboFragment newInstance(String currentDate) {
        KakeiboFragment fragment = new KakeiboFragment();
        //日付の値を取得
        Bundle bundle = new Bundle();
        bundle.putString("data", currentDate);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.memo_index_day)
    TextView textDay;
    @BindView(R.id.memo_index_te)
    TextView textView;

    private Database database; //データベースクラス
    private String day;        //日付
    private String data;       //日付表示形式変更後

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        day = bundle.getString("data");
        data = new SimpleDateFormat("MMdd", Locale.getDefault()).format(new Date(day));
        String textdata = new SimpleDateFormat("MM月dd日", Locale.getDefault()).format(new Date(day));
        textDay.setText(textdata);
        LogUtil.debug("KakeiboFragment", "日付は"+ data);

        database = Database.getInstance(getActivity());
        Cursor cursor = database.retrieveByDate(data);
        StringBuilder text = new StringBuilder();
        if (cursor.moveToFirst()) {
            do {
                text.append(cursor.getString(1) + " ");
                text.append(cursor.getInt(2) + "\n");
            } while (cursor.moveToNext());
        }
        textView.setText(text);
    }

    //収支入力ページ移行
    @OnClick(R.id.sifuto)
    void btnSifutoClick(){
        navigateToFragment(SifutoFragment.newInstance());
    }

    //収支入力ページ移行
    @OnClick(R.id.syuusi)
    void btnSyuusiClick(){
        navigateToFragment(SyuusiFragment.newInstance(data));
    }
}