package com.example.kakeibo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // データーベースのバージョン
    private static final int DATABASE_VERSION = 1;

    // データーベース名
    private static final String DATABASE_NAME = "Kakeibo.db";

    //支出テーブル
    public static final String SPENDING_TABLE = "spending_table";
    //収入テーブル
    public static final String INCOME_TABLE = "income_table";
    //メモテーブル
    public static final String MEMO_TABLE = "memo_table";

    //支出カラム
    public static final String SPENDING_ID = "s_id";
    public static final String SPENDING_CATEGORY = "s_category";
    public static final String SPENDING_PRICE = "s_price";
    public static final String SPENDING_DAY = "s_day";

    //収入カラム
    public static final String INCOME_ID = "i_id";
    public static final String INCOME_CATEGORY = "i_category";
    public static final String INCOME_PRICE = "i_price";
    public static final String INCOME_DAY = "i_day";

    //メモカラム
    public static final String MEMO_ID = "m_id";
    public static final String MEMO_DATA = "memo";
    public static final String MEMO_DAY = "m_day";

    //支出テーブル作成
    private static final String SQL_CREATE_SPENDING = " CREATE TABLE IF NOT EXISTS " + SPENDING_TABLE +
            " ( " +
            SPENDING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            SPENDING_CATEGORY + " TEXT NOT NULL, " +
            SPENDING_PRICE + " INTEGER NOT NULL, " +
            SPENDING_DAY + " TEXT" +
            " ) ";

    //収入テーブル作成
    private static final String SQL_CREATE_INCOME = " CREATE TABLE IF NOT EXISTS " + INCOME_TABLE +
            " ( " +
            INCOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            INCOME_CATEGORY + " TEXT NOT NULL, " +
            INCOME_PRICE + " INTEGER NOT NULL, " +
            INCOME_DAY + " TEXT" +
            " ) ";

    //メモテーブル作成
    private static final String SQL_CREATE_MEMO = " CREATE TABLE IF NOT EXISTS " + MEMO_TABLE +
            " ( " +
            MEMO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MEMO_DATA + " TEXT, " +
            MEMO_DAY + " TEXT " +
            " ) ";

    DatabaseHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //テーブルクリエイト
        db.execSQL(SQL_CREATE_SPENDING);
        db.execSQL(SQL_CREATE_INCOME);
        db.execSQL(SQL_CREATE_MEMO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別
        db.execSQL("DROP TABLE IF EXISTS " + SPENDING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + INCOME_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MEMO_TABLE);

        //アップデートしたテーブルを作成
        onCreate(db);
    }
}