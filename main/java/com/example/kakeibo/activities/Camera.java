package com.example.kakeibo.activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.kakeibo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Camera extends AppCompatActivity {

    @BindView(R.id.camera)
    ImageView camera;

    private Uri image_Uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        ButterKnife.bind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 200 && resultCode == RESULT_OK){
            camera.setImageURI(image_Uri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if (requestCode == 200 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            onCameraImageClick(camera);
        }
    }

    /**
     *  画像部分がタップされた時の処理メソッド
     */

    public void onCameraImageClick(View view){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, 2000);
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date(System.currentTimeMillis());
        String nowStr = dateFormat.format(now);
        String fileName = "UseCameraActivityPhoto_" + nowStr + ".jpg";
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        ContentResolver resolver = getContentResolver();
        image_Uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_Uri);
        startActivityForResult(intent, 200);
    }
}
