package com.xiaoxin.feng.jhang.app;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author: xiaoxin
 * date: 2018/10/8
 * describe:
 * 修改内容:
 */
public abstract class BaseBindingActivity<B extends ViewDataBinding>  extends AppCompatActivity implements IBaseBindingView{

    protected Activity        mActivity;
    protected B mBinding;

    /**
     * 上次点击时间
     */
    private long lastClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        Bundle bundle = getIntent().getExtras();
        initData(bundle);
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(this),
                bindLayout(), null, false);
        super.setContentView(mBinding.getRoot());
        bindingView();
        doBusiness();
    }

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }

    @Override
    public void onClick(final View view) {
        if (!isFastClick()) onWidgetClick(view);
    }

    /**
     * 视图点击事件
     *
     * @param view 视图
     */
    public void onWidgetClick(final View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinding != null)
            mBinding.unbind();
    }
}
