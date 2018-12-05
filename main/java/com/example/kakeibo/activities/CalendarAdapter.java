package com.example.kakeibo.activities;

import android.database.Cursor;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kakeibo.R;
import com.example.kakeibo.database.DatabaseManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends BaseAdapter {

    private List<Date> dateArray = new ArrayList();
    private Context mContext;
    private DataManager dataManager;
    private LayoutInflater mLayoutInflater;
    private SimpleDateFormat format;
    private DatabaseManager mDatabase;

    //カスタムセルを拡張したらここでWigetを定義
    private static class ViewHolder {
        public TextView dateText;
        //public ImageView imageView;  //メモが入っていたら✎マーク
    }

    public CalendarAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        dataManager = new DataManager();
        dateArray = dataManager.getDays();
    }

    @Override
    public int getCount() {
        return dateArray.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.cell, null);
            holder = new ViewHolder();
            holder.dateText = convertView.findViewById(R.id.dateText);
            //holder.imageView = convertView.findViewById(R.id.image_data);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        //セルのサイズを指定
        float dp = mContext.getResources().getDisplayMetrics().density;
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(parent.getWidth()/7 - (int)dp, (parent.getHeight() - (int)dp * dataManager.getWeeks() ) / dataManager.getWeeks());
        convertView.setLayoutParams(params);

        //日付のみ表示させる
        SimpleDateFormat dateFormat = new SimpleDateFormat("d", Locale.US);
        holder.dateText.setText(dateFormat.format(dateArray.get(position)));

        //当月以外のセルをグレーアウト
        if (dataManager.isCurrentMonth(dateArray.get(position))){
            convertView.setBackgroundColor(Color.WHITE);
        }else {
            convertView.setBackgroundColor(Color.LTGRAY);
        }

        //当日はマゼンタに
        if (dataManager.isToday(dateArray.get(position))){
            convertView.setBackgroundColor(Color.MAGENTA);
        }

        //日曜日を赤、土曜日を青に
        int colorId;
        switch (dataManager.getDayOfWeek(dateArray.get(position))){
            case 1:
                colorId = Color.RED;
                break;
            case 7:
                colorId = Color.BLUE;
                break;

            default:
                colorId = Color.BLACK;
                break;
        }
        holder.dateText.setTextColor(colorId);

        //------------------------------------
        String dataText = getTitle();
        String yy = dataText.substring(0, 4);
        String mm = dataText.substring(5, 7);
        /**
         * メモが記入されている日付は✎マーク出力
         */
        //Cursor cursor = mDatabase.retrieveByDateM(yy, mm, dateFormat)

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return dateArray.get(position);
    }

    //表示月を取得
    public String getTitle(){
        format = new SimpleDateFormat("yyyy.MM", Locale.US);
        return format.format(dataManager.mCalendar.getTime());
    }

//    public void setTitle(SimpleDateFormat format){
//        this.format = format;
//    }

    //翌月表示
    public void nextMonth(){
        dataManager.nextMonth();
        dateArray = dataManager.getDays();
        this.notifyDataSetChanged();
    }

    //前月表示
    public void prevMonth(){
        dataManager.prevMonth();
        dateArray = dataManager.getDays();
        this.notifyDataSetChanged();
    }
}
