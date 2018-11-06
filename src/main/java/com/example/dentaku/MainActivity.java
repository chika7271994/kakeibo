package com.example.dentaku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.kakeibo.R;

public class MainActivity extends AppCompatActivity {
    /**
     * 入力した値を表示するためのView
     */
    /*private TextView displayInput;
    private TextView displayResult;
    private Calculator calculator = new Calculator();*/

    private String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.dentaku);
        setContentView(R.layout.memo_index);

        Intent intent = getIntent();
        currentDate = intent.getStringExtra("date");

        // 準備を色々する
        /*displayInput = (TextView) findViewById(R.id.display_input);
        displayResult = (TextView) findViewById(R.id.display_result);

        // 数字をおした時の動作を関連付ける
        findViewById(R.id.num_0).setOnClickListener(new OnClickListenerForNumber(0));
        findViewById(R.id.num_1).setOnClickListener(new OnClickListenerForNumber(1));
        findViewById(R.id.num_2).setOnClickListener(new OnClickListenerForNumber(2));
        findViewById(R.id.num_3).setOnClickListener(new OnClickListenerForNumber(3));
        findViewById(R.id.num_4).setOnClickListener(new OnClickListenerForNumber(4));
        findViewById(R.id.num_5).setOnClickListener(new OnClickListenerForNumber(5));
        findViewById(R.id.num_6).setOnClickListener(new OnClickListenerForNumber(6));
        findViewById(R.id.num_7).setOnClickListener(new OnClickListenerForNumber(7));
        findViewById(R.id.num_8).setOnClickListener(new OnClickListenerForNumber(8));
        findViewById(R.id.num_9).setOnClickListener(new OnClickListenerForNumber(9));
        // 小数点をおした時の動作
        findViewById(R.id.dot).setOnClickListener(new OnClickListenerForDot());

        // TODO: 演算子の関連付けをする
        findViewById(R.id.addition).setOnClickListener(new OnClickListenerForOperation(Calculator.Operation.PLUS));
        findViewById(R.id.subtraction).setOnClickListener(new OnClickListenerForOperation(Calculator.Operation.SUBTRACTION));
        findViewById(R.id.multiplication).setOnClickListener(new OnClickListenerForOperation(Calculator.Operation.MULTIPLICATION));
        findViewById(R.id.division).setOnClickListener(new OnClickListenerForOperation(Calculator.Operation.DIVISION));

        // TODO: クリアボタンの関連付けをする
        findViewById(R.id.clear).setOnClickListener(new OnClickListenerForClear());

        // TODO: =の関連付けをする
        findViewById(R.id.equal).setOnClickListener(new OnClickListenerForEqual());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    *//**
     * 数字のボタンが押された時の動きを設定するためのクラス
     *//*
    private class OnClickListenerForNumber implements View.OnClickListener {
        private final int number;

        public OnClickListenerForNumber(int number) {
            this.number = number;
        }

        *//**
         * 数字を文字列として取得するためのメソッド
         *
         * @return numberをString型に変換したもの
         *//*
        protected String getStringNumber() {
            return Integer.toString(number);
        }

        *//**
         * このメソッドに数字のボタンが押された時の動きを書く
         *//*
        @Override
        public void onClick(View v) {
            String stringNumber = getStringNumber();
            calculator.setValue(number);
            displayInput.append(stringNumber);
        }
    }

    *//**
     * このクラスはちょっとややこしい話になるので今回は何もしない
     *//*
    private class OnClickListenerForDot extends OnClickListenerForNumber {

        public OnClickListenerForDot() {
            super(-1);
        }

        protected String getStringNumber() {
            return ".";
        }
    }

    *//**
     * 演算子用のOnClickListener
     *//*
    private class OnClickListenerForOperation implements View.OnClickListener {
        *//**
         * どの演算子なのかを判別するためのもの
         *//*
        private final Calculator.Operation operation;

        public OnClickListenerForOperation(Calculator.Operation operation) {
            this.operation = operation;
        }

        @Override
        public void onClick(View v) {
            // TODO: 演算子が押された時の動作を書く
            displayInput.append(operation.getSymbol());
            calculator.setOperation(operation);
        }
    }

    private class OnClickListenerForClear implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            displayInput.setText("");
            calculator.clear();
        }
    }

    private class OnClickListenerForEqual implements View.OnClickListener {

        *//**
         * 文字列を数字に変換する
         *
         * @param stringNumber
         * @return
         *//*
        private double convertNumber(String stringNumber) {
            return Double.valueOf(stringNumber);
        }

        @Override
        public void onClick(View v) {
            // TODO: 計算結果を表示するようにする
            Double result = (double) calculator.calculate();
            displayResult.setText("=" + String.valueOf(result));
            calculator.clear();*/
        //}
    }
}
