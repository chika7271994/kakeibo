package com.example.kakeibo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Database {

    private static Database database;
    private TestOpenHelper helper;
    private SQLiteDatabase db;
    //private EditText editHinmoku, editPrice;
    //private TextView textView;

    public static synchronized Database getInstance(Context context){
        if (database == null){
            database = new Database(context);
        }
        return database;
    }

    private Database(Context context){
        helper = new TestOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public boolean addEntry(String category, int price){
        final ContentValues values = new ContentValues();
        values.put(TestOpenHelper.COLUMN_CATEGORY, category);
        values.put(TestOpenHelper.COLUMN_PRICE,price);
        return database.insert(TestOpenHelper.TABLE_NAME, null, values) != -1;
    }

    public Cursor retrieveAllEntries() {
        return database.rawQuery("SELECT * FROM " + TestOpenHelper.TABLE_NAME, null);
    }
}


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_index);

        //フラグメント
        //addFragment(KakeiboFragment.newInstance());

        editHinmoku = findViewById(R.id.editText1);
        editPrice = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView1);

        Button inputButton = findViewById(R.id.syuusi_input);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (helper == null) {
                    helper = new TestOpenHelper(getApplicationContext());
                }

                if (db == null) {
                    db = helper.getWritableDatabase();
                }

                String hinmoku = editHinmoku.getText().toString();
                String price = editPrice.getText().toString();

                insertData(db, hinmoku, Integer.valueOf(price));
            }
        });

        Button readButton = findViewById(R.id.syuusi_back);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });
    }

    private void readData() {
        if (helper == null) {
            helper = new TestOpenHelper(getApplicationContext());
        }

        if (db == null) {
            db = helper.getReadableDatabase();
        }

        Cursor cursor = db.query(
                "kakeibodb",
                new String[]{"category", "price"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
            sbuilder.append(": ");
            sbuilder.append(cursor.getInt(1));
            sbuilder.append("\n");
            cursor.moveToNext();
        }

        cursor.close();
        textView.setText(sbuilder.toString());
    }

    private void insertData(SQLiteDatabase db, String hinmoku, int price) {
        ContentValues values = new ContentValues();
        values.put("hinmoku", hinmoku);
        values.put("price", price);

        db.insert("kakeibodb", null, values);
    }
}
*/