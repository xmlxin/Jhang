package com.xiaoxin.guid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaoxin.guid.Api;
import com.xiaoxin.guid.GsonUtils;
import com.xiaoxin.guid.HttpGet;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.adapter.ResultAdapter;
import com.xiaoxin.guid.base.BaseActivity;
import com.xiaoxin.guid.bean.ResultBean;
import com.xiaoxin.guid.util.AppUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiaoxin
 * date: 2018/10/10
 * describe:第四步：导诊结果
 * 修改内容:
 */
public class ResultActivity extends BaseActivity {

    private RecyclerView mRvResult;
    private ResultAdapter mResultAdapter;
    private List<ResultBean.DataBean> mKsdmListBeans;
    int mSymptomCourseId;
    private String mSex;
    private String mSymptomIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initAdapter() {

        mKsdmListBeans = new ArrayList<>();
        mResultAdapter = new ResultAdapter(R.layout.item_result_list, mKsdmListBeans, this);
        mRvResult.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mResultAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        mRvResult.setAdapter(mResultAdapter);

        mResultAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (AppUtil.checkPackInfo(ResultActivity.this,"com.baidu.BaiduMap")) {
                    AppUtil.startActivity(ResultActivity.this,true);
                }else if (AppUtil.checkPackInfo(ResultActivity.this,"com.autonavi.minimap")){
                    AppUtil.startActivity(ResultActivity.this,false);
                }else {
                    Toast.makeText(ResultActivity.this,"没有安装地图软件",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * 请求对应部位症状列表的具体一项病症
     */
    private void loadBz() {
        new Thread(() -> {
            Map<String, String> map = new HashMap<>();
            map.put("sex", mSex);
            map.put("symptomCourseId",mSymptomCourseId+"");
            map.put("symptomIds", mSymptomIds);
            runOnUI(HttpGet.get(Api.FOUR, map));
        }).start();
    }

    /**
     * 更新ui
     *
     * @param result
     */
    private void runOnUI(String result) {
        runOnUiThread(() -> {
            ResultBean bwBean = GsonUtils.GsonToBean(result, ResultBean.class);

            mResultAdapter.setNewData(bwBean.getData());
        });
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

        mSymptomCourseId = getIntent().getIntExtra("symptomCourseId",0);
        mSex = getIntent().getStringExtra("sex");
        mSymptomIds = getIntent().getStringExtra("symptomIds");

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_result;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mRvResult = findViewById(R.id.rv_result);

        TextView title = findViewById(R.id.tv_title_name);
        title.setText("导诊结果");
        title.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void doBusiness() {
        initAdapter();
        loadBz();
    }
}
