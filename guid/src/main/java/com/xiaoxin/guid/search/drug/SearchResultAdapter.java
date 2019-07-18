package com.xiaoxin.guid.search.drug;

import android.content.Context;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.guid.R;

import java.util.List;


/**
 * @author: xiaoxin
 * date: 2018/8/1
 * describe:搜索结果
 * 修改内容:
 */

public class SearchResultAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;
    public SearchResultAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.tv_search_result,item)
                .addOnClickListener(R.id.tv_search_result);
    }

}
