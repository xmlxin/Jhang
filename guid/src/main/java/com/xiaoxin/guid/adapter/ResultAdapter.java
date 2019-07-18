package com.xiaoxin.guid.adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.ResultBean;
import com.xiaoxin.guid.util.AppUtil;

import java.util.List;


/**
 * @author: xiaoxin
 * date: 2018/8/1
 * describe:导诊结果
 * 修改内容:
 */

public class ResultAdapter extends BaseQuickAdapter<ResultBean.DataBean, BaseViewHolder> {

    private Context mContext;
    private FlowAdapter mFlowAdapter;
    public ResultAdapter(int layoutResId, @Nullable List<ResultBean.DataBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ResultBean.DataBean item) {

        helper.setText(R.id.tv_diseaseName,item.getDiseaseName()+"");

        RecyclerView flowLayout = (RecyclerView)helper.getView(R.id.rv_ks);
        mFlowAdapter = new FlowAdapter(R.layout.tv,item.getDeptList());
        flowLayout.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        flowLayout.setAdapter(mFlowAdapter);

        mFlowAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (AppUtil.checkPackInfo(mContext,"com.baidu.BaiduMap")) {
                    AppUtil.startActivity(mContext,true);
                }else if (AppUtil.checkPackInfo(mContext,"com.autonavi.minimap")){
                    AppUtil.startActivity(mContext,false);
                }else {
                    Toast.makeText(mContext,"没有安装地图软件",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
