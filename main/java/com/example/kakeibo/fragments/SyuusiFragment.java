package com.example.kakeibo.fragments;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kakeibo.R;
import com.example.kakeibo.database.Database;
import com.example.kakeibo.database.TestOpenHelper;
import com.example.kakeibo.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//InputFragmentとする

public class SyuusiFragment extends BaseFragment {
    private static final String TAG = SyuusiFragment.class.getSimpleName();

    public static SyuusiFragment newInstance() { return  new  SyuusiFragment(); }

    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.textSyuusi)
    TextView textView;

    private Database database;

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.syuusi_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.debug(TAG, "onActivityCreated");
        database = Database.getInstance(getActivity());

        addData();
    }

    private void addData(){
        //ここがスラックで話した部分です
        String category = editText1.getText().toString();
        String price = editText2.getText().toString();
        long i = Integer.valueOf(price);
        database.addEntry(category, i);
    }

    @OnClick(R.id.syuusi_input)
    void onAddClick() {
        navigateToFragment(KakeiboFragment.newInstance());
    }
}
