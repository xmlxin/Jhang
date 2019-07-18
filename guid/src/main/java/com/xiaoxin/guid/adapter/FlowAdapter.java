package com.xiaoxin.guid.adapter;

import android.content.Context;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.ResultBean;

import java.util.List;


/**
 * @author: xiaoxin
 * date: 2018/8/1
 * describe:签约会员
 * 修改内容:
 */

public class FlowAdapter extends BaseQuickAdapter<ResultBean.DataBean.DeptListBean, BaseViewHolder> {

    private Context mContext;
    public FlowAdapter(int layoutResId, @Nullable List<ResultBean.DataBean.DeptListBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, ResultBean.DataBean.DeptListBean item) {

        helper.setText(R.id.tv_ks,item.getJuniorName())
                .addOnClickListener(R.id.tv_ks);
    }

}
