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

public class InputFragment extends BaseFragment {

    private static final String TAG = InputFragment.class.getSimpleName();

    public static InputFragment newInstance() {
        return new InputFragment();
    }

    @BindView(R.id.textView1)
    TextView mInput;

    private Database database;

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
        LogUtil.debug(TAG, "onActivityCreated");
        database = Database.getInstance(getActivity());
        addDummyData();
    }

    private void addDummyData() {
        /*View v = getLayoutInflater().inflate(R.layout.syuusi_page,null);
        EditText category = ButterKnife.findById(v, R.id.editText1);
        EditText price = ButterKnife.findById(v, R.id.editText2);
        String cate = category.getText().toString();
        String pri = price.getText().toString();
        int i = Integer.parseInt(pri);*/
        database.addEntry("?",'?' );
    }

    @OnClick(R.id.show_data)
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
