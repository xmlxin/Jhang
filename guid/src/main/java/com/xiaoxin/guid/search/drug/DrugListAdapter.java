package com.xiaoxin.guid.search.drug;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.Nullable;
import android.util.SparseBooleanArray;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.search.DrugTypeBean;

import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/8/1
 * describe:签约会员
 * 修改内容:
 */

public class DrugListAdapter extends BaseQuickAdapter<DrugTypeBean.DataBean.ItemsBean, BaseViewHolder> {

    private SparseBooleanArray mBooleanArray;
    private int mLastCheckedPosition = -1;

    private Context mContext;
    public DrugListAdapter(int layoutResId, @Nullable List<DrugTypeBean.DataBean.ItemsBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugTypeBean.DataBean.ItemsBean item) {


        if (!mBooleanArray.get(helper.getLayoutPosition())) {
            helper.setVisible(R.id.view_line,false)
                    .setTextColor(R.id.tv_name,mContext.getResources().getColor(R.color.color_99))
                    .itemView.setBackgroundColor(mContext.getResources().getColor(R.color.bg_f8f8f8));
        } else {
            helper.setVisible(R.id.view_line,true)
                    .setTextColor(R.id.tv_name,mContext.getResources().getColor(R.color.black))
                    .itemView.setBackgroundColor(Color.WHITE);

        }
        helper.setText(R.id.tv_name,item.getName());
    }

    public void setSize(int size) {

        mBooleanArray = new SparseBooleanArray(size);
    }
    public void setItemChecked(int position) {
        boolean b = mBooleanArray.get(position);
        if (b) {
           return;
        }
        mBooleanArray.put(position, true);

        if (mLastCheckedPosition > -1) {
            mBooleanArray.put(mLastCheckedPosition, false);
            notifyItemChanged(mLastCheckedPosition);
        }
        notifyDataSetChanged();
        mLastCheckedPosition = position;
    }
}
