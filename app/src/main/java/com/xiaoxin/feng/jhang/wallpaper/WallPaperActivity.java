package com.xiaoxin.feng.jhang.wallpaper;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.util.UriPathUtil;
import com.xiaoxin.feng.jhang.wallpaper.VideoLiveWallpaper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class WallPaperActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private CheckBox mCbVoice;
    private Button mSelectFile;
    private TextView mFilePath;
    private String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_paper);
        mCbVoice = (CheckBox) findViewById(R.id.id_cb_voice);
        mSelectFile = (Button) findViewById(R.id.bt_selectFile);
        mFilePath = (TextView) findViewById(R.id.tv_filePath);

        Log.e(TAG, "onCreate: ");

        mSelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(intent,1);

                showFileChooser();
            }
        });

        mCbVoice.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {
                            // 静音
                            VideoLiveWallpaper.voiceSilence(getApplicationContext());
                        } else {
                            VideoLiveWallpaper.voiceNormal(getApplicationContext());
                        }
                    }
                });
    }

    /**
     * 设置壁纸
     * @param view
     */
    public void setVideoToWallPaper(View view) {
        Log.e(TAG, "setVideoToWallPaper: mPath"+mPath);
        VideoLiveWallpaper.setToWallPaper(this,mPath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult:111 code"+resultCode );
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Log.e(TAG, "File Uri: " + uri.toString());
                    mPath = null;
                    try {
//                        mPath = getPath(this, uri);
                        mPath= UriPathUtil.getPath(this,uri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG, "File Path: " + mPath);
                    mFilePath.setVisibility(View.VISIBLE);
                    mFilePath.setText(mPath);
                }
                break;
        }
        //super.onActivityResult(requestCode, resultCode, data);

    }


    private static final int FILE_SELECT_CODE = 0;

    /**
     * 查看文件
     */
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult( Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it  Or Log it.
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private void writeMp4ToNative(File file, InputStream is) {

        try {
            FileOutputStream os = new FileOutputStream(file);
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer))!=-1){
                os.write(buffer,0,buffer.length);
            }
            os.flush();
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
