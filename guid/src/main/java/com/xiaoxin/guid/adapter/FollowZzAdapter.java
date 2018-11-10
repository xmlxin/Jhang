package com.xiaoxin.guid.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.BwBean;

import java.util.List;


/**
 * @author: xiaoxin
 * date: 2018/8/1
 * describe:伴随症状
 * 修改内容:
 */

public class FollowZzAdapter extends BaseQuickAdapter<BwBean.DataBean, BaseViewHolder> {


    private Context mContext;
    public FollowZzAdapter(int layoutResId, @Nullable List<BwBean.DataBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BwBean.DataBean item) {

        helper.setText(R.id.tv_name,item.symptomName)
                .addOnClickListener(R.id.iv_right);
        if (item.status) {
            helper.getView(R.id.iv_right).setBackgroundResource(R.drawable.select_file);
        }else {
            helper.getView(R.id.iv_right).setBackgroundResource(R.drawable.un_select);
        }

    }

}
