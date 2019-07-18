package com.xiaoxin.guid.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.xiaoxin.guid.R;
import com.xiaoxin.guid.util.LoadImageUtil;

public class Test1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        setTitle("loading");
        ImageView loading = (ImageView) findViewById(R.id.iv_img);

        LoadImageUtil.loadGif(this,loading);
    }
}
