package com.example.kakeibo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowDataBase extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_kakeibo);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        TestOpenHelper helper = new TestOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        // queryメソッドの実行例
        Cursor c = db.query
                ("kakeibodb",
                new String[] { "hinmoku", "price" },
                        null,
                        null,
                        null,
                        null,
                        null);

        boolean mov = c.moveToFirst();
        while (mov) {
            TextView textView = new TextView(this);
            textView.setText(String.format("%s : %d", c.getString(0),
                    c.getInt(1)));
            mov = c.moveToNext();
            layout.addView(textView);
        }
        c.close();
        db.close();
    }
}
