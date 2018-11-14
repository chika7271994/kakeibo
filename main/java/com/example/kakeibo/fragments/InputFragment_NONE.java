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

public class InputFragment_NONE extends BaseFragment {

    private static final String TAG = InputFragment_NONE.class.getSimpleName();

    public static InputFragment_NONE newInstance() {
        return new InputFragment_NONE();
    }

    @BindView(R.id.textSyuusi)
    TextView mInput;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.editText2)
    EditText editText2;

    private Database database;

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
        addDummyData();
    }

    private void addDummyData() {
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

    @OnClick(R.id.syuusi_input)
    void onShowClick() {
        Cursor cursor = database.retrieveAllEntries();
        StringBuilder text = new StringBuilder();
        if (cursor.moveToFirst()) {
            do {
                text.append(cursor.getString(1) + " ");
                text.append(cursor.getInt(2) + "\n");
            } while (cursor.moveToNext());
        }

        mInput.setText(text);
    }
}
