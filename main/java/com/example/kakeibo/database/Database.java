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

    public boolean addEntry(String category, int price){
        final ContentValues values = new ContentValues();
        values.put(TestOpenHelper.COLUMN_CATEGORY, category);
        values.put(TestOpenHelper.COLUMN_PRICE,price);
        return db.insert(TestOpenHelper.TABLE_NAME, null, values) != -1;
    }

    //データベースの読み込み
    public Cursor retrieveAllEntries() {
        return db.rawQuery("SELECT * FROM " + TestOpenHelper.TABLE_NAME, null);
    }
}