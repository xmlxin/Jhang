package com.xiaoxin.guid;

import android.content.res.AssetManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.xiaoxin.guid.base.BaseActivity;

public class JkktActivity extends BaseActivity {

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_jkkt;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

        //https://blog.csdn.net/jdsjlzx/article/details/51699463
        //https://segmentfault.com/a/1190000009017879
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0,null);
    }

    @Override
    public void doBusiness() {

//        try {
//            AssetManager assetManager = AssetManager.class.newInstance();
//            assetManager.addAssetPath();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
