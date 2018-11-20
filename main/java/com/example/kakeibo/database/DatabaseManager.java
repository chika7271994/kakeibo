package com.example.kakeibo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    private static DatabaseManager databaseManager;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public static synchronized DatabaseManager getInstance(Context context){
        if (databaseManager == null){
            databaseManager = new DatabaseManager(context);
        }
        return databaseManager;
    }

    //書き込み用のSQLite取得
    private DatabaseManager(Context context){
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    /*
    **  ここから書き込み
     */

    //支出データ書き込み
    public boolean addSpending(String category, int price, String day){
        final ContentValues values = new ContentValues();
        values.put(DatabaseHelper.SPENDING_CATEGORY, category);
        values.put(DatabaseHelper.SPENDING_PRICE, price);
        values.put(DatabaseHelper.SPENDING_DAY, day);
        return db.insert(DatabaseHelper.SPENDING_TABLE, null, values) != -1;
    }

    //収入データ書き込み
    public boolean addIncome(String iCategory, int iPrice, String iDay){
        final ContentValues iValues = new ContentValues();
        iValues.put(DatabaseHelper.INCOME_CATEGORY, iCategory);
        iValues.put(DatabaseHelper.INCOME_PRICE, iPrice);
        iValues.put(DatabaseHelper.INCOME_DAY, iDay);
        return db.insert(DatabaseHelper.INCOME_TABLE, null, iValues) != -1;
    }

    //メモデータ書き込み
    public boolean addMemo(String mData, String mDay){
        final ContentValues mValues = new ContentValues();
        mValues.put(DatabaseHelper.MEMO_DATA, mData);
        mValues.put(DatabaseHelper.MEMO_DAY, mDay);
        return db.insert(DatabaseHelper.MEMO_TABLE, null, mValues) != -1;
    }

    /*
     **  ここから読み込み
     */

    //  支出全データベースの読み込み
    public Cursor retrieveAllEntries() {
        return db.rawQuery("SELECT * FROM " + DatabaseHelper.SPENDING_TABLE, null);
    }

    //支出日付毎のデータベースの読み込み用
    public Cursor retrieveByDate(String date) {
        String sql = "SELECT * FROM " +  DatabaseHelper.SPENDING_TABLE + " WHERE " + DatabaseHelper.SPENDING_DAY + " = " + "'" + date + "'";
        return db.rawQuery(sql, null);
    }

    //収入全データベースの読み込み
    public Cursor retriveAllIncome(){
        return db.rawQuery("SELECT * FROM " + DatabaseHelper.INCOME_TABLE, null);
    }

    //収入日付毎のデータベースの読み込み
    public Cursor retrieveByDateI(String date){
        String sql = " SELECT * FROM " + DatabaseHelper.INCOME_TABLE + " WHERE " + DatabaseHelper.INCOME_DAY + " = " + "'" + date + "'";
        return db.rawQuery(sql, null);
    }

    //メモ全データベースの読み込み
    public Cursor retriveAllmemo(){
        return db.rawQuery(" SELECT * FROM " + DatabaseHelper.MEMO_TABLE, null);
    }

    //メモ日付毎のデータベースの読み込み
    public Cursor retrieveByDateM(String date){
        String sql = " SELECT * FROM " + DatabaseHelper.MEMO_TABLE + " WHERE " + DatabaseHelper.MEMO_DAY + " = " + "'" + date + "'";
        return db.rawQuery(sql, null);
    }
}