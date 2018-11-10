package com.xiaoxin.feng.jhang.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.bean.FunctionBean;

import java.util.List;

/**
 * @author: xiaoxin
 * @date: 2018-03-6
 * @description:
 */
public class FunctionAdapter extends BaseQuickAdapter<FunctionBean,BaseViewHolder> {

    public FunctionAdapter(@LayoutRes int layoutResId, @Nullable List<FunctionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FunctionBean item) {

        helper.setText(R.id.bt_function,item.getFunctionName());
        Button button = helper.getView(R.id.bt_function);
        button.getWidth();
        button.getHeight();

        Log.e("xiaoxin", "宽: "+  button.getWidth());
        Log.e("xiaoxin", "高: "+  button.getHeight());
    }
}
