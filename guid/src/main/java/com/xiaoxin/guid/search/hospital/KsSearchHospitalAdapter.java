package com.xiaoxin.guid.search.hospital;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.search.SearchHospitalOneBean;

import java.util.List;


/**
 * @author: xiaoxin
 * date: 2018/8/1
 * describe:查找医院adapter
 * 修改内容:
 */

public class KsSearchHospitalAdapter extends BaseQuickAdapter<SearchHospitalOneBean.DataBean.ItemsBean, BaseViewHolder> {

    private SparseBooleanArray mBooleanArray;
    private int mLastCheckedPosition = -1;

    private Context mContext;
    public KsSearchHospitalAdapter(int layoutResId, @Nullable List<SearchHospitalOneBean.DataBean.ItemsBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchHospitalOneBean.DataBean.ItemsBean item) {

        helper.setText(R.id.tv_ks_name,item.getName())
                .setText(R.id.tv_ks_description,item.getDescription());

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,HostipitalListActivity.class)
                        .putExtra("title",item.getName()));
            }
        });
    }


}
