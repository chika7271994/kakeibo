package com.example.kakeibo.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Database {

    private static Database database;
    private TestOpenHelper helper;
    private SQLiteDatabase db;

    public static synchronized Database getInstance(Context context){
        if (database == null){
            database = new Database(context);
        }
        return database;
    }

    //書き込み用のSQLite取得
    private Database(Context context){
        helper = new TestOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public boolean addEntry(String category, int price, String day){
        final ContentValues values = new ContentValues();
        values.put(TestOpenHelper.COLUMN_CATEGORY, category);
        values.put(TestOpenHelper.COLUMN_PRICE, price);
        values.put(TestOpenHelper.COLUMN_DAY, day);
        return db.insert(TestOpenHelper.TABLE_NAME, null, values) != -1;
    }

    //データベースの読み込み:SyuusiFragment用
    public Cursor retrieveAllEntries() {
        return db.rawQuery("SELECT * FROM " + TestOpenHelper.TABLE_NAME, null);
    }

    //データベースの読み込みKakeiboFragment用
    public Cursor retrieveByDate(String date) {
        String sql = "SELECT * FROM " +  TestOpenHelper.TABLE_NAME + " WHERE " + TestOpenHelper.COLUMN_DAY + " = " + date;
        return db.rawQuery(sql, null);
    }

}