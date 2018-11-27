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
import com.example.kakeibo.database.DatabaseManager;
import com.example.kakeibo.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.alldata_text_sun_spending)
    TextView spending_sun_text;
    @BindView(R.id.alldata_text_income)
    TextView income_text;
    @BindView(R.id.alldata_text_sun_income)
    TextView income_sun_text;

    private DatabaseManager mDatabase;        //データベースクラス
    private String month; //日付取得
    private String data;
    private String yy;
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
        yy = month.substring(0, 4);
        mm = month.substring(5, 7);
        LogUtil.debug("AlldataFragment", "日付は"+ month);
        LogUtil.debug("AlldataFragment", "yyは"+ yy);
        LogUtil.debug("AlldataFragment", "mmは"+ mm);
        mDatabase = DatabaseManager.getInstance(getActivity());
        showData();
    }

    private void showData(){
        //支出データ呼び出し
        Cursor cursor = mDatabase.retrieveDataAll(yy, mm);
        StringBuilder text = new StringBuilder();
        if (cursor.moveToFirst()) {
            do {
                text.append(cursor.getString(1) + " ");
                text.append(cursor.getInt(2) + "\n");
            } while (cursor.moveToNext());
        }
        spending_text.setText(text);

        //支出合計データ呼び出し
        Cursor sCursor = mDatabase.sunSpendingData(yy, mm);
        StringBuilder spendingText = new StringBuilder();
        if (sCursor.moveToFirst()) {
            do {
                //incomeText.append(iCursor.getString(1) + " ");
                spendingText.append(sCursor.getInt(0) + "\n");
            } while (sCursor.moveToNext());
        }
        spending_sun_text.setText(spendingText);

        //収入データ呼び出し
        Cursor iCursor = mDatabase.retrieveDataAllI(yy, mm);
        StringBuilder builder = new StringBuilder();
        if (iCursor.moveToNext()){
            do {
                builder.append(iCursor.getString(1) + " ");
                builder.append(iCursor.getInt(2) + "\n");
            } while (iCursor.moveToNext());
        }
        income_text.setText(builder);

        //収入合計データ呼び出し
        Cursor c = mDatabase.sunIncomeData(yy, mm);
        StringBuilder stringBuilder = new StringBuilder();
        if (c.moveToNext()){
            do {
                stringBuilder.append(c.getInt(0) + "\n");
            } while (c.moveToNext());
        }
        income_sun_text.setText(stringBuilder);
    }

    //SpendingFragmentページに戻る
    @OnClick(R.id.alldata_back)
    void btnBackClick(){
        navigateToFragment(CalendarFragment.newInstance());
    }
}
