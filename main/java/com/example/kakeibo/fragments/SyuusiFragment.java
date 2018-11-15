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
    @BindView(R.id.syuusi_text)
    TextView textView2;

    private Database database;
    private String day;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //値を受け取る
        //dataの中身がnullになる
        Bundle bundle = getArguments();
        if (bundle != null){
            day = bundle.getString("data");
        }else {
            LogUtil.debug("onCreate", "dataの中身は" + day);
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.syuusi_page, container, false);
        ButterKnife.bind(this, view);
        textView2.setText(day);
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
        int i = 0;
        try{
            i = Integer.valueOf(price);
        }catch (Exception e){
            LogUtil.debug("addData", "price の中身は" + price);
        }
        database.addEntry(category,i);
    }

    @OnClick(R.id.syuusi_show)
    void onAddClick() {
        Cursor cursor = database.retrieveAllEntries();
        StringBuilder builder = new StringBuilder();
        if(cursor.moveToFirst()){
            do {
                builder.append(cursor.getString(1) + " ");
                builder.append(cursor.getInt(2) + "\n");
            }while (cursor.moveToNext());
        }
        cursor.close();
        textView.setText(builder.toString());
    }
}
