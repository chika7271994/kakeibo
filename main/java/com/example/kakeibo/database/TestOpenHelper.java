package com.example.kakeibo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Text;

import static java.sql.Types.INTEGER;

public class TestOpenHelper extends SQLiteOpenHelper {

    // データーベースのバージョン
    private static final int DATABASE_VERSION = 1;

    // データーベース名
    private static final String DATABASE_NAME = "Kakeibo.db";

    //テーブル名
    public static final String TABLE_NAME = "kakeibodb";

    //カラム
    public static final String _ID = "_id";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DAY = "day";

    //テーブル作成
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_CATEGORY + " TEXT NOT NULL, " +
            COLUMN_PRICE    + " INTEGER NOT NULL, " +
            COLUMN_DAY      + " TEXT" +
            ")";

    TestOpenHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    // テーブル作成
    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(SQL_CREATE_ENTRIES); }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        //アップデートしたテーブルを作成
        onCreate(db);
    }

    /*public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }*/
}