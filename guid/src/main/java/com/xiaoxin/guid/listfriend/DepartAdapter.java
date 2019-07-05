package com.xiaoxin.guid.listfriend;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xiaoxin.guid.R;

import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/10/27
 * describe:
 * 修改内容:
 */
public class DepartAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private Context mContext;
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;


    public DepartAdapter(List<MultiItemEntity> data, Context ctx) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_bm);
        addItemType(TYPE_LEVEL_1, R.layout.item_name);
        mContext = ctx;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {

        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final DepartmentBean.KcHrDeptBean itemsBean =
                        (DepartmentBean.KcHrDeptBean) item;
                holder.setText(R.id.tag,itemsBean.deptName+"(" + itemsBean.number +")");
                if (itemsBean.isSelect()) {
                    holder.setImageResource(R.id.iv_select_bm,R.drawable.tongxunlu_xuanze);
                }else {
                    holder.setImageResource(R.id.iv_select_bm,R.drawable.tongxunlu_weixuan);
                }

                holder.getView(R.id.iv_select_bm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemsBean.setSelect(!itemsBean.isSelect());

                        List<DepartmentBean.KcHrEmployesBean> subItems = itemsBean.getSubItems();
                        for (int i = 0; i < subItems.size(); i++) {
                            if (itemsBean.isSelect()) {
                                subItems.get(i).setSelect(true);
                            }else {
                                subItems.get(i).setSelect(false);
                            }
                        }
//                        notifyItemChanged(holder.getLayoutPosition());
                        notifyDataSetChanged();
                    }
                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        if (itemsBean.isExpanded()) {
                            collapse(pos, false);
                        } else {
                            expand(pos, false);
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final DepartmentBean.KcHrEmployesBean listBean =
                        (DepartmentBean.KcHrEmployesBean) item;
                holder.setText(R.id.tv_disease_name,listBean.employeName);
                if (listBean.isSelect()) {
                    holder.setImageResource(R.id.iv_select_person,R.drawable.tongxunlu_xuanze);
                }else {
                    holder.setImageResource(R.id.iv_select_person,R.drawable.tongxunlu_weixuan);
                }

//                holder.getView(R.id.iv_select_person).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        listBean.setSelect(!listBean.isSelect());
//                        notifyItemChanged(holder.getLayoutPosition());
//                    }
//                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listBean.setSelect(!listBean.isSelect());
                        notifyItemChanged(holder.getLayoutPosition());
                    }
                });
                break;
        }
    }


}
