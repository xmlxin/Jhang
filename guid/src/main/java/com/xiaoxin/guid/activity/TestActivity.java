package com.xiaoxin.guid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.xiaoxin.guid.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setTitle("sdfsdf");
        ImageView loading = (ImageView) findViewById(R.id.iv_img);

//        LoadImageUtil.loadGif(this,loading);
    }
}
