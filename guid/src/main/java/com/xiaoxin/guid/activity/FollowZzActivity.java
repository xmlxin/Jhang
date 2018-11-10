package com.xiaoxin.guid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaoxin.guid.Api;
import com.xiaoxin.guid.GsonUtils;
import com.xiaoxin.guid.HttpGet;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.adapter.FollowZzAdapter;
import com.xiaoxin.guid.base.BaseActivity;
import com.xiaoxin.guid.bean.BwBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiaoxin
 * date: 2018/10/10
 * describe:第三步：伴随症状
 * 修改内容:
 */
public class FollowZzActivity extends BaseActivity {

    private RecyclerView mRvFollowZz;
    private FollowZzAdapter mFollowZzAdapter;
    private List<BwBean.DataBean> mKsdmListBeans;
    private BwBean.DataBean mBean;
    int mSymptomCourseId;
    private String mSex;
    private Button mBtSure;
    private Map<Integer,String> symptomIds;

    private void initAdapter() {

        mKsdmListBeans = new ArrayList<>();
        mFollowZzAdapter = new FollowZzAdapter(R.layout.item_followzz,mKsdmListBeans,this);
        mRvFollowZz.setLayoutManager(new LinearLayoutManager(this));
        mRvFollowZz.setAdapter(mFollowZzAdapter);
        mFollowZzAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BwBean.DataBean bean = (BwBean.DataBean)adapter.getData().get(position);
                if (bean.status) {
                    symptomIds.remove(bean.id);
                }else {
                    symptomIds.put(bean.id,bean.id+",");
                }
                bean.status = !bean.status;
                mFollowZzAdapter.notifyItemChanged(position);
            }
        });

        mBtSure.setOnClickListener(v -> {
            StringBuilder ids = new StringBuilder();
            for (Map.Entry<Integer, String> m : symptomIds.entrySet()) {
                ids.append(m.getValue());
            }
            Intent intent = new Intent(FollowZzActivity.this,ResultActivity.class);
            intent.putExtra("symptomIds",ids.toString());
            intent.putExtra("sex",mSex);
            intent.putExtra("symptomCourseId",mSymptomCourseId);
            startActivity(intent);
        });
    }

    /**
     * 请求对应部位症状列表的具体一项病症
     */
    private void loadBz() {
        new Thread(() -> {
            Map<String,String> map = new HashMap<>();
            map.put("sex",mSex);
            map.put("symptomCourseId",mBean.symptomCourseId+"");
            runOnUI(HttpGet.get(Api.THREE, map));
        }).start();
    }

    /**
     * 更新ui
     * @param result
     */
    private void runOnUI(String result) {
        runOnUiThread(() ->{
            BwBean bwBean = GsonUtils.GsonToBean(result, BwBean.class);

            mFollowZzAdapter.setNewData(bwBean.getData());
        });
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        mSex = getIntent().getStringExtra("sex");
        mBean = (BwBean.DataBean)getIntent().getSerializableExtra("bean");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_follow_zz;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        TextView title = findViewById(R.id.tv_title_name);
        title.setText("伴随症状");
        title.setOnClickListener(v -> {
            finish();
        });
        mRvFollowZz = findViewById(R.id.rv_follow_zz);
        mBtSure = findViewById(R.id.bt_sure);
    }

    @Override
    public void doBusiness() {

        mSymptomCourseId = mBean.symptomCourseId;

        symptomIds = new HashMap<>();
        initAdapter();
        loadBz();
    }
}
