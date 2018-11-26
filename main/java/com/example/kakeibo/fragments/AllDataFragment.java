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
import com.example.kakeibo.activities.CalendarAdapter;
import com.example.kakeibo.database.DatabaseManager;
import com.example.kakeibo.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

//ひと月ごとの全データ出力

public class AllDataFragment extends BaseFragment {

    public static AllDataFragment newInstance(String month){
        AllDataFragment fragment = new AllDataFragment();
        Bundle bundle = new Bundle();
        bundle.putString("month", month);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.alldata_text_spending)
    TextView spending_text;
    @BindView(R.id.alldata_text_income)
    TextView income_text;

    private DatabaseManager mDatabase;        //データベースクラス
    private String month; //日付取得
    private String mm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alldata, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        month = bundle.getString("month");
        LogUtil.debug("AlldataFragment", "日付は"+ month);
        mDatabase = DatabaseManager.getInstance(getActivity());
        showData();
    }

    private void showData(){
        mm = new SimpleDateFormat("mm月", Locale.getDefault()).format(new Date(month));
        //支出データ呼び出し

        /*
        Cursor cursor = mDatabase.retrieveDataAll(mCalendarAdapter.getTitle());
        StringBuilder text = new StringBuilder();
        if (cursor.moveToFirst()) {
            do {
                text.append(cursor.getString(1) + " ");
                text.append(cursor.getInt(2) + "\n");
            } while (cursor.moveToNext());
        }
        spending_text.setText(text);*/

        //収入データ呼び出し
        /*Cursor iCursor = mDatabase.retriveAllIncome();
        StringBuilder incomeText = new StringBuilder();
        if (iCursor.moveToFirst()) {
            do {
                incomeText.append(iCursor.getString(1) + " ");
                incomeText.append(iCursor.getInt(2) + "\n");
            } while (iCursor.moveToNext());
        }
        income_text.setText(incomeText);*/
    }
}
