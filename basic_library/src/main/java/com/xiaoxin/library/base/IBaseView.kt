package com.xiaoxin.library.base

import android.os.Bundle

import androidx.annotation.LayoutRes
import android.view.View

internal interface IBaseView : View.OnClickListener {

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    fun initData(bundle: Bundle?)

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    fun bindLayout(): Int

    fun setRootLayout(@LayoutRes layoutId: Int)

    /**
     * 初始化 view
     */
    fun initView(savedInstanceState: Bundle?, contentView: View?)

    /**
     * 业务操作
     */
    fun doBusiness()

    /**
     * 视图点击事件
     *
     * @param view 视图
     */
    fun onWidgetClick(view: View)
}
