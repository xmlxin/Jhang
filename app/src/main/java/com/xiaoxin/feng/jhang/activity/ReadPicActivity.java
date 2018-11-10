package com.xiaoxin.feng.jhang.activity;

import android.content.Intent;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.util.UriPathUtil;

import java.io.IOException;

public class ReadPicActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ReadPicActivity";
    private static final int FILE_SELECT_CODE = 0;
    private Button mSelectFile;
    private TextView mResult;
    private String mPath;
    ExifInterface exifInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_pic);
        mSelectFile = (Button) findViewById(R.id.select_file);
        mResult = (TextView) findViewById(R.id.tv_result);

        mSelectFile.setOnClickListener(this);
        mResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.select_file:
                selectPhoto();
                break;
            case R.id.tv_result:
                break;
        }
    }

    /**
     * 查看文件
     */
    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*");
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // Uri fileUri = FileProvider7.getUriForFile(this);
        try {
            startActivityForResult( Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    private void readFile(String filePath) {
        try {
            exifInterface = new ExifInterface(filePath);

            String TAG_APERTURE = exifInterface.getAttribute(ExifInterface.TAG_APERTURE);
            String TAG_DATETIME = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
            String TAG_EXPOSURE_TIME = exifInterface.getAttribute(ExifInterface.TAG_EXPOSURE_TIME);
            String TAG_FLASH = exifInterface.getAttribute(ExifInterface.TAG_FLASH);
            String TAG_FOCAL_LENGTH = exifInterface.getAttribute(ExifInterface.TAG_FOCAL_LENGTH);
            String TAG_IMAGE_LENGTH = exifInterface.getAttribute(ExifInterface.TAG_IMAGE_LENGTH);
            String TAG_IMAGE_WIDTH = exifInterface.getAttribute(ExifInterface.TAG_IMAGE_WIDTH);
            String TAG_ISO = exifInterface.getAttribute(ExifInterface.TAG_ISO);
            String TAG_MAKE = exifInterface.getAttribute(ExifInterface.TAG_MAKE);
            String TAG_MODEL = exifInterface.getAttribute(ExifInterface.TAG_MODEL);
            String TAG_ORIENTATION = exifInterface.getAttribute(ExifInterface.TAG_ORIENTATION);
            String TAG_WHITE_BALANCE = exifInterface.getAttribute(ExifInterface.TAG_WHITE_BALANCE);
            String latitude = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            String latitude_ref = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            String longitude = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            String longitude_ref = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
            String altitidu = exifInterface.getAttribute(ExifInterface.TAG_GPS_ALTITUDE);
            mResult.setText("");
            mResult.append("光圈值:" + TAG_APERTURE+"\n");
            mResult.append("拍摄时间:" + TAG_DATETIME+"\n");
            mResult.append("闪光灯:" + TAG_FLASH+"\n");
            mResult.append("焦距:" + TAG_FOCAL_LENGTH+"\n");
            mResult.append("图片高度:" + TAG_IMAGE_LENGTH+"\n");
            mResult.append("图片宽度:" + TAG_IMAGE_WIDTH+"\n");
            mResult.append("ISO:" + TAG_ISO+"\n");
            mResult.append("设备品牌:" + TAG_MAKE+"\n");
            mResult.append("设备型号:" + TAG_MODEL+"\n");
            mResult.append("旋转角度:" + TAG_ORIENTATION+"\n");
            mResult.append("白平衡:" + TAG_WHITE_BALANCE+"\n");
            if (latitude == null) {
                mResult.append("此图片没有位置信息");
            }else {
                mResult.append("N:" + latitude+"\n");
                mResult.append("E:" + longitude+"\n");
                mResult.append("海拔高度:" + altitidu+"\n");
            }

            Log.i(TAG, "光圈值:" + TAG_APERTURE);
            Log.i(TAG, "拍摄时间:" + TAG_DATETIME);
            Log.i(TAG, "曝光时间:" + TAG_EXPOSURE_TIME);
            Log.i(TAG, "闪光灯:" + TAG_FLASH);
            Log.i(TAG, "焦距:" + TAG_FOCAL_LENGTH);
            Log.i(TAG, "图片高度:" + TAG_IMAGE_LENGTH);
            Log.i(TAG, "图片宽度:" + TAG_IMAGE_WIDTH);
            Log.i(TAG, "ISO:" + TAG_ISO);
            Log.i(TAG, "设备品牌:" + TAG_MAKE);
            Log.i(TAG, "设备型号:" + TAG_MODEL);
            Log.i(TAG, "旋转角度:" + TAG_ORIENTATION);
            Log.i(TAG, "白平衡:" + TAG_WHITE_BALANCE);
            Log.i(TAG, "latitude:" + latitude);
            Log.i(TAG, "latitude_ref:" + latitude_ref);  //N
            Log.i(TAG, "longitude:" + longitude);
            Log.i(TAG, "longitude_ref:" + longitude_ref);  //E

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult:111 code"+resultCode );
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    Log.e(TAG, "onActivityResult: " );
                    Uri uri = data.getData();
                    Log.e(TAG, "File Uri: " + uri.toString());
                    mPath = null;
                    try {
                        mPath = UriPathUtil.getPath(this,uri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG, "File Path: " + mPath);
                    readFile(mPath);
                }
                break;
        }
    }
}
