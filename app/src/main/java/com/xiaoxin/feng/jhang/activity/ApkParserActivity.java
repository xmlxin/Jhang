package com.xiaoxin.feng.jhang.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.databinding.ActivityApkParserBinding;
import com.xiaoxin.feng.jhang.util.UriPathUtil;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.UseFeature;
import java.io.File;
import java.io.IOException;

/**
 * https://github.com/hsiafan/apk-parser
 * https://github.com/jaredrummler/APKParser
 */
public class ApkParserActivity extends AppCompatActivity{

    private static final String TAG = "ApkParserActivity";
    private static final int FILE_SELECT_CODE = 0;
    private String mPath;
    private ActivityApkParserBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_apk_parser);

        mDataBinding.selectFileApk.setOnClickListener(v -> {
            selectPhoto();
        });

        mDataBinding.apkParser.setOnClickListener(v -> {
            apkParser();
        });
    }

    /**
     * 解析apk包信息
     */
    private void apkParser() {
        ApkFile apkFile = null;
        try {
            apkFile = new ApkFile(new File(mPath));
            ApkMeta apkMeta = apkFile.getApkMeta();
            mDataBinding.tvApkInfo.setText("");
            mDataBinding.tvApkInfo.append("app名称："+apkMeta.getLabel()+"\n");
            mDataBinding.tvApkInfo.append("app包名："+apkMeta.getPackageName()+"\n");
            mDataBinding.tvApkInfo.append("versionCode："+apkMeta.getVersionCode()+"\n");
            mDataBinding.tvApkInfo.append("versionName："+apkMeta.getVersionName()+"\n");
            for (UseFeature feature : apkMeta.getUsesFeatures()) {
                System.out.println(feature.getName());
                Log.e(TAG, "apkParser: "+feature.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看文件
     */
    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // Uri fileUri = FileProvider7.getUriForFile(this);
        try {
            startActivityForResult( Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult:111 code" + resultCode);
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    Log.e(TAG, "onActivityResult: ");
                    Uri uri = data.getData();
                    Log.e(TAG, "File Uri: " + uri.toString());
                    mPath = null;
                    try {
                        mPath = UriPathUtil.getPath(this, uri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG, "File Path: " + mPath);
                }
                break;
        }
    }
}
