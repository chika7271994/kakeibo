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

import butterknife.BindView;
import butterknife.ButterKnife;

//ひと月ごとの全データ出力

public class AllDataFragment extends BaseFragment {

    public static AllDataFragment newInstance(){ return new AllDataFragment(); }

    @BindView(R.id.alldata_text_spending)
    TextView spending_text;
    @BindView(R.id.alldata_text_income)
    TextView income_text;

    private DatabaseManager mDatabase; //データベースクラス

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

        mDatabase = DatabaseManager.getInstance(getActivity());
        showData();
    }

    private void showData(){
        //支出データ呼び出し
        Cursor cursor = mDatabase.retrieveAllEntries();
        StringBuilder text = new StringBuilder();
        if (cursor.moveToFirst()) {
            do {
                text.append(cursor.getString(1) + " ");
                text.append(cursor.getInt(2) + "\n");
            } while (cursor.moveToNext());
        }
        spending_text.setText(text);

        //収入データ呼び出し
        Cursor iCursor = mDatabase.retriveAllIncome();
        StringBuilder incomeText = new StringBuilder();
        if (iCursor.moveToFirst()) {
            do {
                incomeText.append(iCursor.getString(1) + " ");
                incomeText.append(iCursor.getInt(2) + "\n");
            } while (iCursor.moveToNext());
        }
        income_text.setText(incomeText);
    }
}
