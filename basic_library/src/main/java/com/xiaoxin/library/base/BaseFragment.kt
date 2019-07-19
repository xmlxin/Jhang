package com.xiaoxin.library.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentTransaction

import com.blankj.utilcode.util.ClickUtils
import com.trello.rxlifecycle3.components.support.RxFragment

abstract class BaseFragment : RxFragment(), IBaseView {

    private val mClickListener = View.OnClickListener { v -> onWidgetClick(v) }

    lateinit var mActivity: Activity
    lateinit var mInflater: LayoutInflater
    protected var mContentView: View? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = (context as Activity?)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            val isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN)
            val ft = fragmentManager!!.beginTransaction()
            if (isSupportHidden) {
                ft.hide(this)
            } else {
                ft.show(this)
            }
            ft.commitAllowingStateLoss()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: ")
        mInflater = inflater
        setRootLayout(bindLayout())
        return mContentView
    }

    @SuppressLint("ResourceType")
    override fun setRootLayout(@LayoutRes layoutId: Int) {
        if (layoutId <= 0) return
        mContentView = mInflater.inflate(layoutId, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ")
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        initData(bundle)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated: ")
        super.onActivityCreated(savedInstanceState)
        initView(savedInstanceState, mContentView)
        doBusiness()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: ")
        if (mContentView != null) {
            (mContentView!!.parent as ViewGroup).removeView(mContentView)
        }
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "onSaveInstanceState: ")
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }

    fun applyDebouncingClickListener(vararg views: View) {
        ClickUtils.applyGlobalDebouncing(views, mClickListener)
    }

    fun <T : View> findViewById(@IdRes id: Int): T {
        if (mContentView == null) throw NullPointerException("ContentView is null.")
        return mContentView!!.findViewById(id)
    }

    companion object {

        private val TAG = "BaseFragment"
        private val STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"
    }
}
