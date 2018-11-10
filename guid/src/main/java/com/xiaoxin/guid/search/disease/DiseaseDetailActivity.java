package com.xiaoxin.guid.search.disease;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xiaoxin.guid.Api;
import com.xiaoxin.guid.GsonUtils;
import com.xiaoxin.guid.HttpGet;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.DiseaseDetailBean;
import com.xiaoxin.guid.search.disease.adapter.DiseaseDetailAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiaoxin
 * date: 2018/10/30
 * describe: 疾病详情页
 * 修改内容:
 */
public class DiseaseDetailActivity extends AppCompatActivity {

    private static final String TAG = "DiseaseDetailActivity";
    private TabLayout mTabLayout;
    private int mId;
    private String title;
    private DiseaseDetailBean mDetailBean;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_detail);

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mId = getIntent().getIntExtra("id",0);
        title = getIntent().getStringExtra("title");

        TextView tvDiseaseName = findViewById(R.id.tv_disease_name);
        tvDiseaseName.setText(title);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.viewPager);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.jianjie));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.zhengzhuang));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.bingyin));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.zhenduan));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.zhiliao));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.sehgnhuo));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.yifang));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.yisheng));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.yiyuan));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.yaopin));

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        if (mId != 0) {
            loadDiseaseInfo();
        }
    }

    private void loadDiseaseInfo() {
        new Thread(() -> {
            Map<String,String> map = new HashMap<>();
            map.put("id",mId+"");
            map.put("content_status","1");
            map.put("display_course_entry","1");
            map.put("dxa_entry","event_homepage_sepcial_area_click_查疾病");
            map.put("mc","000000006fba5445ffffffff946c9cbf");
            map.put("cn","General");
            map.put("hardName","SM-G955F");
            map.put("ac","d5424fa6-adff-4b0a-8917-4264daf4a348");
            map.put("bv","2017");
            map.put("vc","7.6.7");
            map.put("vs","4.4.2");
//            String result = HttpGet.get(Api.SEARCH_DISEASE, map);
//            Log.e(TAG, "loadDiseaseInfo: "+result );
            runOnUI(HttpGet.get(Api.SEARCH_DISEASE, map));
        }).start();
    }

    /**
     * 更新ui
     * @param result
     */
    private void runOnUI(String result) {
        runOnUiThread(() ->{
            mDetailBean = GsonUtils.GsonToBean(result, DiseaseDetailBean.class);
            DiseaseDetailBean.DataBean.ItemsBean itemsBean = mDetailBean.getData().getItems().get(0);
            itemsBean.getTitle();
//            SearchHospitalDao.insertJbingDetail(result, itemsBean.getTitle());
            mViewPager.setAdapter(new DiseaseDetailAdapter(getSupportFragmentManager(),itemsBean,
                    getString(R.string.jianjie),
                    getString(R.string.zhengzhuang),
                    getString(R.string.bingyin),
                    getString(R.string.zhenduan),
                    getString(R.string.zhiliao),
                    getString(R.string.sehgnhuo),
                    getString(R.string.yifang),
                    getString(R.string.yisheng),
                    getString(R.string.yiyuan),
                    getString(R.string.yaopin)));
        });
    }
}
