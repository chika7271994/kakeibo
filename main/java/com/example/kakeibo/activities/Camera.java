package com.example.kakeibo.activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.kakeibo.R;
import com.example.kakeibo.database.DatabaseManager;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Camera extends AppCompatActivity {

    @BindView(R.id.camera)
    ImageView camera;

    private Bitmap bitmap;
    private byte[] bytes;
    private  String dayData;
    private String mm;
    private String dd;
    private String yy;
    private DatabaseManager mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        Intent intent = getIntent();
        dayData = intent.getStringExtra("DayData");
        ButterKnife.bind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 200 && resultCode == RESULT_OK){
            bitmap = data.getParcelableExtra("data");
            camera.setImageBitmap(bitmap);
        }
    }

    private void addCameraData(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        bytes = baos.toByteArray();
        yy = dayData.substring(0, 4);
        mm = dayData.substring(5, 7);
        dd = dayData.substring(8, 10);
        int year = Integer.valueOf(yy);
        int month = Integer.valueOf(mm);
        int days = Integer.valueOf(dd);
        mDatabase.addCamera("test", bytes, year, month, days);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if (requestCode == 200 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            onCameraImageClick(camera);
            //addCameraData();
        }
    }

    /**
     *  カメラマーク画像部分がタップされた時の処理メソッド
     */

    public void onCameraImageClick(View view){
        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, 2000);
            return;*/
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 200);
        }




        //日時データとファイル名形成
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date(System.currentTimeMillis());
        String nowStr = dateFormat.format(now);
        String fileName = "UseCameraActivityPhoto_" + nowStr + ".jpg";

        //ContentValuesオブジェクトを生成
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        //ContentResolverオブジェクトを生成
        ContentResolver resolver = getContentResolver();
        image_Uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_Uri);
        startActivityForResult(intent, 200);*/
    //}
}
