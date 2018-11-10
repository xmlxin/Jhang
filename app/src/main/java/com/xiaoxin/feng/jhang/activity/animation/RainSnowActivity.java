package com.xiaoxin.feng.jhang.activity.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.widget.rain.RainView;
import com.xiaoxin.feng.jhang.widget.snow.SnowView;

public class RainSnowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain_snow);

        boolean rain = getIntent().getBooleanExtra("rain",false);

        RainView rainView = findViewById(R.id.rain);
        SnowView sonwView = findViewById(R.id.snow);
        if (rain) {
            sonwView.setVisibility(View.GONE);
        }else {
            rainView.setVisibility(View.GONE);
        }

    }
}
