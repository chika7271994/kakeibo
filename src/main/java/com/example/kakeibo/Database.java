package com.example.kakeibo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Database extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_index);

        //DB作成
        TestOpenHelper helper = new TestOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        final EditText editText = (EditText)findViewById(R.id.editText1);
        final EditText editPrice = (EditText)findViewById(R.id.editText2);

        Button insertButton = findViewById(R.id.syuusi);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hinmoku = editText.getText().toString();
                String price = editPrice.getText().toString();

                ContentValues insertValues = new ContentValues();
                insertValues.put("hinmoku", hinmoku);
                insertValues.put("price", price);
                long id = db.insert("kakeibodb", hinmoku, insertValues);
            }
        });

        Button showButton = (Button)findViewById(R.id.memo);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbIntent = new Intent(getApplication(), ShowDataBase.class);
                startActivity(dbIntent);
            }
        });
    }
}