package com.xiaoxin.guid.adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.SparseBooleanArray;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.BwBean;

import java.util.List;


/**
 * @author: xiaoxin
 * date: 2018/8/1
 * describe:签约会员
 * 修改内容:
 */

public class BzAdapter extends BaseQuickAdapter<BwBean.DataBean, BaseViewHolder> {

    private SparseBooleanArray mBooleanArray;
    private int mLastCheckedPosition = -1;

    private Context mContext;
    public BzAdapter(int layoutResId, @Nullable List<BwBean.DataBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BwBean.DataBean item) {

//
//        if (!mBooleanArray.get(helper.getLayoutPosition())) {
//            helper.setVisible(R.id.view_line,false)
//                    .setTextColor(R.id.tv_name,Color.BLACK)
//                    .itemView.setBackgroundColor(mContext.getResources().getColor(R.color.bg_app));
//        } else {
//            helper.setVisible(R.id.view_line,false)
//                    .setTextColor(R.id.tv_name,mContext.getResources().getColor(R.color.colorAccent))
//                    .itemView.setBackgroundColor(Color.WHITE);
//
//        }
        helper.setText(R.id.tv_name,item.name);
    }

    public void setSize(int size) {

        mBooleanArray = new SparseBooleanArray(size);
    }
    public void setItemChecked(int position) {
        mBooleanArray.put(position, true);

        if (mLastCheckedPosition > -1) {
            mBooleanArray.put(mLastCheckedPosition, false);
            notifyItemChanged(mLastCheckedPosition);
        }
        notifyDataSetChanged();
        mLastCheckedPosition = position;
    }
}
