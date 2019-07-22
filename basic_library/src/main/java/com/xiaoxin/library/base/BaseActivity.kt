package com.xiaoxin.library.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.blankj.utilcode.util.AdaptScreenUtils

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.xiaoxin.library.util.ActivityManager

/**
 * @author: xiaoxin
 * date: 2018/10/30
 * describe: 基础 activity
 * 修改内容:
 */
abstract class BaseActivity : RxAppCompatActivity(), IBaseView {

    lateinit var mContentView: View
    lateinit var mActivity: Activity

    /**
     * 上次点击时间
     */
    private var lastClick: Long = 0

    /**
     * 判断是否快速点击
     *
     * @return `true`: 是<br></br>`false`: 否
     */
    private val isFastClick: Boolean
        get() {
            val now = System.currentTimeMillis()
            if (now - lastClick >= 200) {
                lastClick = now
                return false
            }
            return true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        ActivityManager.instance.addActivity(this)
//        this.window
//                .decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        MIUISetStatusBarLightMode(this.window, true)
        val bundle = intent.extras
        initData(bundle)
        setRootLayout(bindLayout())
        initView(savedInstanceState, mContentView)
        doBusiness()
    }

    @SuppressLint("ResourceType")
    override fun setRootLayout(@LayoutRes layoutId: Int) {
        if (layoutId <= 0) return
        mContentView = LayoutInflater.from(this).inflate(layoutId, null)
        setContentView(mContentView)
    }

    override fun onClick(view: View) {
        if (!isFastClick) onWidgetClick(view)
    }

    /**
     * 视图点击事件
     *
     * @param view 视图
     */
    override fun onWidgetClick(view: View) {

    }

    companion object {

        fun MIUISetStatusBarLightMode(window: Window?, dark: Boolean): Boolean {
            var result = false
            if (window != null) {
                val clazz = window.javaClass
                try {
                    var darkModeFlag:Int
                    val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                    val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                    darkModeFlag = field.getInt(layoutParams)
                    val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
                    if (dark) {
                        extraFlagField.invoke(window, darkModeFlag, darkModeFlag)//状态栏透明且黑色字体
                    } else {
                        extraFlagField.invoke(window, 0, darkModeFlag)//清除黑色字体
                    }
                    result = true
                } catch (e: Exception) {

                }

            }
            return result
        }
    }

    /**
     * 以宽1080 适配
     */
    override fun getResources(): Resources {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 1080)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.instance.removeActivity(this)
    }

}
