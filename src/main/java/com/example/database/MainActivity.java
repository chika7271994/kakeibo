package com.example.database;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kakeibo.R;
import com.example.shifto.shifto;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_index);

        TestOpenHelper helper = new TestOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        final EditText kingaku = (EditText) findViewById(R.id.editText1);

        Button button = (Button) findViewById(R.id.syuusi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kingk = kingaku.getText().toString();

                ContentValues insertValues = new ContentValues();
                insertValues.put("kingk",kingk);
                long id = db.insert("kakeibo", kingk, insertValues);
            }
        });
    }
}
