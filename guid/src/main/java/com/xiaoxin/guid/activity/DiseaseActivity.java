package com.xiaoxin.guid.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaoxin.guid.Api;
import com.xiaoxin.guid.GsonUtils;
import com.xiaoxin.guid.HttpGet;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.adapter.BzAdapter;
import com.xiaoxin.guid.base.BaseActivity;
import com.xiaoxin.guid.bean.BwBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiaoxin
 * date: 2018/10/10
 * describe:第二步：病程
 * 修改内容:
 */
public class DiseaseActivity extends BaseActivity {

    private RecyclerView mRvBz;
    private BzAdapter mBzAdapter;
    private List<BwBean.DataBean> mKsdmListBeans;
    private BwBean.DataBean mBean;
    private String mSex;


    private void initAdapter() {

        mKsdmListBeans = new ArrayList<>();
        mBzAdapter = new BzAdapter(R.layout.item_disease,mKsdmListBeans,this);
        mRvBz.setLayoutManager(new LinearLayoutManager(this));
        mRvBz.setAdapter(mBzAdapter);

        mBzAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BwBean.DataBean bean = ( BwBean.DataBean)adapter.getData().get(position);
                Intent intent = new Intent(DiseaseActivity.this,FollowZzActivity.class);
                intent.putExtra("bean",bean);
                intent.putExtra("sex",mSex);
                intent.putExtra("symptomCourseId",bean.symptomCourseId);
                startActivity(intent);
            }
        });
    }

    /**
     * 请求对应部位症状列表的具体一项病症
     */
    private void loadBz() {
        new Thread(() -> {
            Map<String,String> map = new HashMap<>();
            map.put("sex",mSex);
            map.put("symptomId",mBean.id+"");
            runOnUI(HttpGet.get(Api.SECOND, map));
        }).start();
    }

    /**
     * 更新ui
     * @param result
     */
    private void runOnUI(String result) {
        runOnUiThread(() ->{
            BwBean bwBean = GsonUtils.GsonToBean(result, BwBean.class);

            mBzAdapter.setNewData(bwBean.getData());
            mBzAdapter.setSize(bwBean.getData().size());
        });
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        mSex = getIntent().getStringExtra("sex");
        mBean = (BwBean.DataBean)getIntent().getSerializableExtra("bean");

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_disease;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mRvBz = findViewById(R.id.rv_bz);

        TextView title = findViewById(R.id.tv_title_name);
        title.setText("病症");
        title.setOnClickListener(v -> {
            finish();
        });

        initAdapter();
        loadBz();
    }

    @Override
    public void doBusiness() {

    }
}
