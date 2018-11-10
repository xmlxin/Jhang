package com.xiaoxin.guid.search.hospital;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.search.HospitalListBean;

import java.util.List;


/**
 * @author: xiaoxin
 * date: 2018/8/1
 * describe:医院列表adapter
 * 修改内容:
 */

public class HospitalListAdapter extends BaseQuickAdapter<HospitalListBean.DataBean.ItemsBean, BaseViewHolder> {

    private SparseBooleanArray mBooleanArray;
    private int mLastCheckedPosition = -1;

    private Context mContext;
    public HospitalListAdapter(int layoutResId, @Nullable List<HospitalListBean.DataBean.ItemsBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalListBean.DataBean.ItemsBean item) {

        helper.setText(R.id.tv_ks_name,item.getHospital_name())
                .setText(R.id.tv_ks_description,item.getAddress())
                .setText(R.id.tv_gradle,item.getHospital_grade());

        helper.itemView.setOnClickListener(v ->{
            mContext.startActivity(new Intent(mContext,HospitalDetailActivity.class)
            .putExtra("hospitalId",item.getHospital_id()));
        });
    }


}
