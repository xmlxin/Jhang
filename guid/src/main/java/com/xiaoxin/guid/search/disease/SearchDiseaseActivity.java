package com.xiaoxin.guid.search.disease;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xiaoxin.guid.Api;
import com.xiaoxin.guid.GsonUtils;
import com.xiaoxin.guid.HttpGet;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.search.DiseaseBean;
import com.xiaoxin.guid.db.SearchHospitalDao;
import com.xiaoxin.guid.util.AppUtil;
import com.xiaoxin.guid.widget.SideBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchDiseaseActivity extends AppCompatActivity {

    private static final String TAG = "SearchDiseaseActivity";

    private RecyclerView mRvSearchDisease;
    private List<DiseaseBean.DataBean.ItemsBean> diseaseList;
    private SearchDiseaseMulAdapter mSearchDiseaseAdapter;
    private DiseaseBean mDiseaseBean;
    private ArrayList<MultiItemEntity> mMultiItemEntities;
    private SideBar mSideBar;
    private TextView mDialog;
    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow()
                .getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_search_disease);

        mRvSearchDisease = findViewById(R.id.rv_search_disease);
        mDialog = findViewById(R.id.dialog);


        mSideBar = findViewById(R.id.sideBar);

        mMultiItemEntities = new ArrayList<>();
        String json = AppUtil.getJson("disease_type.json", this);
        mDiseaseBean = GsonUtils.GsonToBean(json, DiseaseBean.class);
        Log.e(TAG, "onCreate: "+ mDiseaseBean);
        List<DiseaseBean.DataBean.ItemsBean> items = mDiseaseBean.getData().getItems();
        int count = 0;
        for (int i = 0; i < items.size(); i++) {
            DiseaseBean.DataBean.ItemsBean itemsBean = items.get(i);
            mMultiItemEntities.add(itemsBean);
            List<DiseaseBean.DataBean.ItemsBean.ListBean> list = items.get(i).getList();
            count += list.size();
            for (int j = 0; j < list.size(); j++) {
                DiseaseBean.DataBean.ItemsBean.ListBean listBean = list.get(j);
                mMultiItemEntities.add(listBean);

            }
        }
        Log.e(TAG, "疾病数量 "+count );

        initAdapter();


        mSideBar.setTextView(mDialog);

        //设置右侧SideBar触摸监听
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = mSearchDiseaseAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }

            }
        });

    }

    private void initAdapter() {
        diseaseList = new ArrayList<>();
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mSearchDiseaseAdapter = new SearchDiseaseMulAdapter(mMultiItemEntities,this);
        mRvSearchDisease.setLayoutManager(manager);
        mRvSearchDisease.setAdapter(mSearchDiseaseAdapter);

    }

    private void search() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = AppUtil.getJson("disease_type.json", SearchDiseaseActivity.this);
                DiseaseBean mDiseaseBean1 = GsonUtils.GsonToBean(json, DiseaseBean.class);
                List<DiseaseBean.DataBean.ItemsBean> items = mDiseaseBean1.getData().getItems();

                for (int i = 0; i < items.size(); i++) {

                    List<DiseaseBean.DataBean.ItemsBean.ListBean> list = items.get(i).getList();
                    for (int j = 0; j < list.size(); j++) {
                        DiseaseBean.DataBean.ItemsBean.ListBean listBean = list.get(j);
                        Map<String,String> map = new HashMap<>();
                        map.put("id",listBean.getId()+"");
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
                        String result = HttpGet.get(Api.SEARCH_DISEASE, map);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        SearchHospitalDao.insertJbingDetail(listBean.getId()+"",result,listBean.getTitle());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//
                    }
                }

                Log.e(TAG, "loadBz: 数据加载完毕" );
            }

        }).start();
    }

}
