package com.xiaoxin.library.base

import android.os.Bundle
import android.view.View

import com.blankj.swipepanel.SwipePanel
import com.blankj.utilcode.util.SizeUtils
import com.xiaoxin.library.R

/**
 * @author: xiaoxin
 * date: 2019/7/19
 * describe: 侧滑返回 activity 需要NoActionBar主题，
 * 修改内容:
 */
abstract class BaseBackActivity : BaseActivity() {

    /**
     * 返回true 侧滑返回
     * @return
     */
    abstract val isSwipeBack: Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<View>(android.R.id.content).setBackgroundColor(resources.getColor(R.color.mediumGray))
        initSwipeBack()
    }

    private fun initSwipeBack() {
        if (isSwipeBack) {
            val swipeLayout = SwipePanel(this)
            swipeLayout.setLeftDrawable(R.drawable.base_back)
            swipeLayout.setLeftEdgeSize(SizeUtils.dp2px(100f))
            swipeLayout.wrapView(findViewById<View>(android.R.id.content))
            swipeLayout.setOnFullSwipeListener { direction ->
                swipeLayout.close(direction)
                finish()
            }
        }
    }


}
