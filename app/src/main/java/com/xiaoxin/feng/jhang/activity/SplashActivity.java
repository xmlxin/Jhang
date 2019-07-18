package com.xiaoxin.feng.jhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.xiaoxin.feng.jhang.MainActivity;
import com.xiaoxin.feng.jhang.widget.MyDrawStrategy;

import site.gemus.openingstartanimation.OpeningStartAnimation;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//        OpeningStartAnimation openingStartAnimation3 = new OpeningStartAnimation.Builder(this)
//                .setDrawStategy(new NormalDrawStrategy())//NormalDrawStrategy RotationDrawStrategy
//                .setColorOfAppIcon(Color.parseColor("#00334060"))
//                .setColorOfAppName(Color.parseColor("#00334060"))
//                .setAppName("jhang")
//                .setAppStatement("敲代码的小新")
//                .setColorOfAppStatement(Color.parseColor("#00334060"))
//                .setAppIcon(getResources().getDrawable(R.mipmap.ic_launcher))
//                .setColorOfAppStatement(Color.parseColor("#00000000"))
//                .setAnimationFinishTime(3000)
//                .create();

        OpeningStartAnimation openingStartAnimation = new OpeningStartAnimation.Builder(this)
                .setDrawStategy(new MyDrawStrategy())
                .setAppName("Jhang")
                .setAppStatement("敲代码的小新")
                .create();//NormalDrawStrategy RedYellowBlueDrawStrategy LineDrawStrategy RotationDrawStrategy
        openingStartAnimation.show(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },1500);
    }
}
