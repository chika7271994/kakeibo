package com.example.kakeibo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.kakeibo.CalendarAdapter;
import com.example.kakeibo.Kakeibo;
import com.example.kakeibo.R;
import com.example.kakeibo.fragments.MainFragment;

public class BaseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private TextView titleText;
    private Button prevButton, nextButton;
    private CalendarAdapter mCalendarAdapter;
    private GridView calendarGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = findViewById(R.id.titleText);
        prevButton = findViewById(R.id.prevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.prevMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.nextMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        calendarGridView = findViewById(R.id.calendarGridView);
        mCalendarAdapter = new CalendarAdapter(this);
        calendarGridView.setAdapter(mCalendarAdapter);
        titleText.setText(mCalendarAdapter.getTitle());

        calendarGridView.setOnItemClickListener(this);

        //メインフラグメント画面表示
        addFragment(MainFragment.newInstance());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getApplicationContext(), Kakeibo.class);
        intent.putExtra("date", mCalendarAdapter.getItem(position).toString());
        startActivity(intent);
    }

    public void addFragment(Fragment fragment){
        final String aaa = fragment.getClass().getName();
        final String bbb = aaa.substring(aaa.lastIndexOf(".")+ 1);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.base, fragment, bbb)
                .disallowAddToBackStack()
                .commit();
    }

    public void replaceFragment(Fragment fragment){
        final String aaa = fragment.getClass().getName();
        final String bbb = aaa.substring(aaa.lastIndexOf(".") + 1);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.base, fragment, bbb)
                .commit();
    }
}
