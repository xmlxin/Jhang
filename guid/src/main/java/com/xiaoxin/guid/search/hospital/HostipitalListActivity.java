package com.xiaoxin.guid.search.hospital;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoxin.guid.Api;
import com.xiaoxin.guid.GsonUtils;
import com.xiaoxin.guid.HttpGet;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.base.BaseActivity;
import com.xiaoxin.guid.bean.search.HospitalListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiaoxin
 * date: 2018/10/27
 * describe: 查找医院第二步:医院列表
 * 修改内容:
 */
public class HostipitalListActivity extends BaseActivity {

    private RecyclerView mRvHosipitalList;
    private HospitalListAdapter mHospitalListAdapter;
    private List<HospitalListBean.DataBean.ItemsBean> mBeanList;
    private String mTitle;

    @Override
    public void initData(@Nullable Bundle bundle) {

        mTitle = getIntent().getStringExtra("title");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_hostipital_list;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

        mRvHosipitalList = findViewById(R.id.rv_hospital_list);
        TextView title = findViewById(R.id.tv_title_name);
        title.setText("查医院");
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void doBusiness() {
        loadHospitalList();
        initAdapter();
    }

    private void initAdapter() {
        mBeanList = new ArrayList<>();
        mHospitalListAdapter = new HospitalListAdapter(R.layout.item_hospital_list,
                mBeanList,this);
        mRvHosipitalList.setLayoutManager(new LinearLayoutManager(this));
        mRvHosipitalList.setAdapter(mHospitalListAdapter);

        mHospitalListAdapter.addHeaderView(getHeadView());
    }

    private View getHeadView() {
        View view = getLayoutInflater().inflate(R.layout.item_hosipital_list_head,
                (ViewGroup) mRvHosipitalList.getParent(), false);
        TextView tjks = view.findViewById(R.id.tv_tjks);
        tjks.setText("推荐科室:"+mTitle);
        TextView tvAddress = view.findViewById(R.id.tv_address);
        tvAddress.setText("济南");
        return view;
    }

    private void loadHospitalList() {
        new Thread(() -> {
            Map<String,String> map = new HashMap<>();
            map.put("q",mTitle);
            map.put("page_index","1");
            map.put("items_per_page","1000");//不做分页请求

            map.put("dxa_entry","event_homepage_sepcial_area_click_查医院");
            map.put("mc","000000006fba5445ffffffff946c9cbf");
            map.put("cn","General");
            map.put("hardName","SM-G955F");
            map.put("ac","d5424fa6-adff-4b0a-8917-4264daf4a348");
            map.put("bv","2017");
            map.put("vc","7.6.7");
            map.put("vs","4.4.2");
            runOnUI(HttpGet.get(Api.HOSPITAL_LIST, map));
        }).start();
    }

    /**
     * 更新ui
     * @param result
     */
    private void runOnUI(String result) {
        runOnUiThread(() ->{
            HospitalListBean drugDetailBean = GsonUtils.GsonToBean(result, HospitalListBean.class);

            if (drugDetailBean.getData() != null && drugDetailBean.getData().getItems() != null) {
                mHospitalListAdapter.setNewData(drugDetailBean.getData().getItems());
            }

        });
    }

}
