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
import com.xiaoxin.guid.adapter.SelKsFirstAdapter;
import com.xiaoxin.guid.adapter.SelKsSecondAdapter;
import com.xiaoxin.guid.base.BaseActivity;
import com.xiaoxin.guid.bean.BwBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuidanceActivity extends BaseActivity {

    private static final String TAG = "GuidanceActivity";

    private SelKsFirstAdapter mSelKsFirstAdapter;
    private SelKsSecondAdapter mSelKsSecondAdapter;
    private List<String> mListBeans;
    private List<BwBean.DataBean> mKsdmListBeans;
    private RecyclerView mRvBw;
    private RecyclerView mRvBwZz;
    private String mSex;
    private String mBw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initAdapter() {

        mListBeans = new ArrayList<>();
        String[] stringArray;
        if ("w".equals(mSex)) {
            stringArray = getResources().getStringArray(R.array.woman);
        }else {
            stringArray = getResources().getStringArray(R.array.man);
        }
//        String[] stringArray = getResources().getStringArray(R.array.man);
        for (int i = 0; i < stringArray.length; i++) {
            mListBeans.add(stringArray[i]);
        }
        mSelKsFirstAdapter = new SelKsFirstAdapter(R.layout.item_ks_first,mListBeans,this);
        mRvBw.setLayoutManager(new LinearLayoutManager(this));
        mRvBw.setAdapter(mSelKsFirstAdapter);
        mSelKsFirstAdapter.setSize(mListBeans.size());
        mSelKsFirstAdapter.setItemChecked(0);
        loadBwzz(mListBeans.get(0));

        mKsdmListBeans = new ArrayList<>();
        mSelKsSecondAdapter = new SelKsSecondAdapter(R.layout.item_ks_first,mKsdmListBeans,this);
        mRvBwZz.setLayoutManager(new LinearLayoutManager(this));
        mRvBwZz.setAdapter(mSelKsSecondAdapter);

        mSelKsFirstAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mSelKsFirstAdapter.setItemChecked(position);

                String bw = (String) adapter.getData().get(position);
                loadBwzz(bw);
            }
        });

        mSelKsSecondAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                BwBean.DataBean bean = ( BwBean.DataBean)adapter.getData().get(position);
                Intent intent = new Intent(GuidanceActivity.this,DiseaseActivity.class);
                intent.putExtra("bean",bean);
                intent.putExtra("sex",mSex);
                startActivity(intent);

            }
        });
    }

    /**
     * 请求对应部位症状列表
     * @param bw
     */
    private void loadBwzz(String bw) {
        mBw = bw;
        new Thread(() -> {
            Map<String,String> map = new HashMap<>();
            map.put("sex",mSex);
            map.put("bodyPartName",bw);
            runOnUI(HttpGet.get(Api.FIRST, map));
        }).start();
    }

    /**
     * 更新ui
     * @param result
     */
    private void runOnUI(String result) {
        runOnUiThread(() ->{
            BwBean bwBean = GsonUtils.GsonToBean(result, BwBean.class);

            mSelKsSecondAdapter.setNewData(bwBean.getData());
            mSelKsSecondAdapter.setSize(bwBean.getData().size());
//            Dbdao.insertSymptom(bwBean.getData(),mSex,mBw);
        });
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

        mSex = getIntent().getStringExtra("sex");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_duidance;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mRvBw = findViewById(R.id.rv_zzg);
        mRvBwZz = findViewById(R.id.rv_zzg_ms);
        TextView title = findViewById(R.id.tv_title_name);
        title.setText("主症状列表");
        title.setOnClickListener(v -> {
            finish();
        });

    }

    @Override
    public void doBusiness() {
        initAdapter();
    }
}
