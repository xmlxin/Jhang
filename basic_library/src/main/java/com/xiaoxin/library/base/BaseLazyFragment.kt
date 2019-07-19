package com.xiaoxin.library.base

import android.util.Log

abstract class BaseLazyFragment : BaseFragment() {

    private var isDataLoaded: Boolean = false

    abstract fun doLazyBusiness()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        Log.d(TAG, "setUserVisibleHint: $isVisibleToUser")
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mContentView != null && !isDataLoaded) {
            doLazyBusiness()
            isDataLoaded = true
        }
    }

    override fun doBusiness() {
        if (userVisibleHint && !isDataLoaded) {
            doLazyBusiness()
            isDataLoaded = true
        }
    }

    companion object {

        private val TAG = "BaseLazyFragment"
    }
}
