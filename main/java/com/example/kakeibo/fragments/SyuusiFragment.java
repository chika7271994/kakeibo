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
import com.example.kakeibo.database.Database;
import com.example.kakeibo.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//InputFragmentとする

public class SyuusiFragment extends BaseFragment {

    private static final String TAG = SyuusiFragment.class.getSimpleName();

    public static SyuusiFragment newInstance(String day) {
        SyuusiFragment fragment = new SyuusiFragment();
        //値を受け取る
        Bundle bundle = new Bundle();
        bundle.putString("data", day);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.textSyuusi)
    TextView textView;
    @BindView(R.id.syuusi_text)
    TextView textView2;

    private Database database;
    private String day;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        day = bundle.getString("data");
    }

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
        View view = inflater.inflate(R.layout.syuusi_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.syuusi_input)
    void onClick() {
            LogUtil.debug(TAG, "onActivityCreated");
            database = Database.getInstance(getActivity());
            addData();
        }


    private void addData(){
        String category = editText1.getText().toString();
        String price = editText2.getText().toString();
        //final String data = new SimpleDateFormat("MM月dd日", Locale.getDefault()).format(new Date(day));
        int i = Integer.valueOf(price);
        LogUtil.debug("addData", "categoryは" + category);
        LogUtil.debug("addData", "priceは" + price);
        LogUtil.debug("addData", "日付は"+ day);
        database.addEntry(category, i, day);
    }

    @OnClick(R.id.syuusi_show)
    void onAddClick() {
        Cursor cursor = database.retrieveByDate(day);
        StringBuilder builder = new StringBuilder();
        if(cursor.moveToFirst()) {
           // if (day.equals(cursor.getString(3))) {
                do {
                    builder.append(cursor.getString(1) + " ");
                    builder.append(cursor.getInt(2) + "\n");
                } while (cursor.moveToNext());
          //  }
        }
        textView.setText(builder.toString());
    }
}
