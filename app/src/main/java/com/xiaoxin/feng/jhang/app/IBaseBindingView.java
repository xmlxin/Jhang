package com.xiaoxin.feng.jhang.app;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/06/27
 *     desc  :
 * </pre>
 */
interface IBaseBindingView extends View.OnClickListener {

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    void initData(@Nullable final Bundle bundle);

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    int bindLayout();

    /**
     * 初始化 id
     */
    void bindingView();

    /**
     * 业务操作
     */
    void doBusiness();

    /**
     * 视图点击事件
     *
     * @param view 视图
     */
    void onWidgetClick(final View view);
}
