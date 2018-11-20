package com.example.kakeibo.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kakeibo.R;
import com.example.kakeibo.database.DatabaseManager;
import com.example.kakeibo.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//収入ページ

public class IncomeFragment extends BaseFragment {

    private static final String TAG = IncomeFragment.class.getSimpleName();

    public static IncomeFragment newInstance(String day) {
        IncomeFragment fragment = new IncomeFragment();
        //値を受け取る
        Bundle bundle = new Bundle();
        bundle.putString("data", day);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.editText_i)
    EditText editText1;
    @BindView(R.id.editText_i2)
    EditText editText2;
    @BindView(R.id.textIncome)
    TextView textView;

    private DatabaseManager mDatabase; //データベースクラス
    private String day;                //日付

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //日付の値を取得
        Bundle bundle = getArguments();
        day = bundle.getString("data");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //レイアウト作成
        View view = inflater.inflate(R.layout.income_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.income_input)
    void onClick() {
        LogUtil.debug(TAG, "onActivityCreated");
        mDatabase = DatabaseManager.getInstance(getActivity());
        addIncome();
    }

    private void addIncome(){
        //データベースに書き込み
        String category = editText1.getText().toString();
        String price = editText2.getText().toString();
        int i = Integer.valueOf(price);
        LogUtil.debug("addIncome", "categoryは" + category);
        LogUtil.debug("addIncome", "priceは" + price);
        LogUtil.debug("addIncome", "日付は"+ day);
        mDatabase.addIncome(category, i, day);
    }

    //入力したデータベースの出力
    @OnClick(R.id.income_show)
    void onAddClick() {
        Cursor cursor = mDatabase.retrieveByDateI(day);
        StringBuilder builder = new StringBuilder();
        if(cursor.moveToFirst()) {
            do {
                builder.append(cursor.getString(1) + " ");
                builder.append(cursor.getString(2) + "\n");
            } while (cursor.moveToNext());
        }
        textView.setText(builder.toString());
    }

    //支出ページに戻る
    @OnClick(R.id.income_back)
    void btnBackClick(){
        navigateToFragment(SpendingFragment.newInstance(day));
    }
}
