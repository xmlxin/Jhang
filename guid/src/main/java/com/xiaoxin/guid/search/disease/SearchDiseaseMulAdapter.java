package com.xiaoxin.guid.search.disease;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.search.DiseaseBean;

import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/10/27
 * describe:
 * 修改内容:
 */
public class SearchDiseaseMulAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private Context mContext;
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SearchDiseaseMulAdapter(List<MultiItemEntity> data, Context ctx) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_search_disease_list);
        addItemType(TYPE_LEVEL_1, R.layout.item_disease_name_list);
        mContext = ctx;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {

        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final DiseaseBean.DataBean.ItemsBean itemsBean =
                        (DiseaseBean.DataBean.ItemsBean) item;
                holder.setText(R.id.tag,itemsBean.getIndex());
                break;
            case TYPE_LEVEL_1:

                final DiseaseBean.DataBean.ItemsBean.ListBean listBean =
                        (DiseaseBean.DataBean.ItemsBean.ListBean) item;
                holder.setText(R.id.tv_disease_name,listBean.getTitle());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mContext.startActivity(new Intent(mContext,DiseaseDetailActivity.class)
                                        .putExtra("id",listBean.getId())
                                        .putExtra("title",listBean.getTitle()));
                    }
                });
                break;
        }
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            MultiItemEntity multiItemEntity1 = mData.get(i);
            if (multiItemEntity1 instanceof DiseaseBean.DataBean.ItemsBean) {

                DiseaseBean.DataBean.ItemsBean multiItemEntity = (DiseaseBean.DataBean.ItemsBean)mData.get(i);
                String sortStr = multiItemEntity.getIndex();
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == section) {
                    return i;
                }
            }
//            String sortStr = mData.get(i).getLetters();
        }
        return -1;
    }
}
