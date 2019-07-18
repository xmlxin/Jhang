package com.xiaoxin.feng.jhang.adapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.feng.jhang.R;

import java.util.List;

/**
 * @author: xiaoxin
 * @date: 2018-03-6
 * @description:
 */
public class TextAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public TextAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.tv_sty,item);
    }
}
