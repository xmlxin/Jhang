package com.xiaoxin.feng.jhang.activity.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.app.BaseBindingActivity;
import com.xiaoxin.feng.jhang.databinding.ActivityAnimationBinding;

public class AnimationActivity extends BaseBindingActivity<ActivityAnimationBinding> {


    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_animation;
    }

    @Override
    public void bindingView() {

        setTitle("UI特效");
        mBinding.btHead.setOnClickListener(v ->{
            startActivity(new Intent(this,HeadWaveActivity.class));
        });
        mBinding.tvAutoText.setOnClickListener(v ->{
            startActivity(new Intent(this,AutoLineLayoutActivity.class));
        });
        mBinding.tvEdit.setOnClickListener(v -> {
            startActivity(new Intent(this,EditActivity.class));
        });
        mBinding.tvRain.setOnClickListener(v ->{
            startActivity(new Intent(this,RainSnowActivity.class)
            .putExtra("rain",true));
        });
        mBinding.tvSnow.setOnClickListener(v ->{
            startActivity(new Intent(this,RainSnowActivity.class)
                    .putExtra("rain",false));
        });
        mBinding.tvShare.setOnClickListener(v -> {
            startActivity(new Intent(this,ShareButtonActivity.class));
        });
    }

    @Override
    public void doBusiness() {

    }
}
