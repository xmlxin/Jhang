package com.xiaoxin.library.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewStub
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ColorUtils
import com.xiaoxin.library.R

/**
 * @author: xiaoxin
 * date: 2019/7/19
 * describe: title 侧滑返回 activity
 * 修改内容:
 */
abstract class BaseTitleActivity : BaseBackActivity() {

    //设置是否可以滑动
    var isSupportScroll = true
    private lateinit var baseTitleRootLayout: CoordinatorLayout
    private lateinit var baseTitleToolbar: Toolbar
    private lateinit var baseTitleContentView: FrameLayout
    private lateinit var mViewStub: ViewStub

    override val isSwipeBack: Boolean
        get() = true

    //设置标题
    abstract fun bindTitle(): CharSequence

    @SuppressLint("ResourceType")
    override fun setRootLayout(@LayoutRes layoutId: Int) {
        super.setRootLayout(R.layout.common_activity_title)

        baseTitleRootLayout = this.findViewById(R.id.baseTitleRootLayout)
        baseTitleToolbar = findViewById(R.id.baseTitleToolbar)
        if (layoutId > 0) {
            mViewStub = if (isSupportScroll) {
                findViewById(R.id.baseTitleStubScroll)
            } else {
                findViewById(R.id.baseTitleStubNoScroll)
            }
            mViewStub.visibility = View.VISIBLE
            baseTitleContentView = findViewById(R.id.commonTitleContentView)
            LayoutInflater.from(this).inflate(layoutId, baseTitleContentView)
        }
        setTitleBar()
        BarUtils.setStatusBarColor(this, ColorUtils.getColor(R.color.title))
        BarUtils.addMarginTopEqualStatusBarHeight(baseTitleRootLayout)
    }

    private fun setTitleBar() {
        setSupportActionBar(baseTitleToolbar)
        val titleBar = supportActionBar
        if (titleBar != null) {
            titleBar.setDisplayHomeAsUpEnabled(true)
            titleBar.title = bindTitle()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
