package com.xiaoxin.animation;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityOptionsCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class AnimationMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_animation_main);

        ImageView imgbg = findViewById(R.id.iv_body);
        imgbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //单个元素共享
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(AnimationMainActivity.this, imgbg, "img");
                Intent intent = new Intent(AnimationMainActivity.this, BigImageActivity.class);
                startActivity(intent, optionsCompat.toBundle());
            }
        });


    }
}
