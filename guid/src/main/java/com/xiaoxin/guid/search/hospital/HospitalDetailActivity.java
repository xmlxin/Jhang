package com.xiaoxin.guid.search.hospital;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoxin.guid.Api;
import com.xiaoxin.guid.GsonUtils;
import com.xiaoxin.guid.HttpGet;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.base.BaseActivity;
import com.xiaoxin.guid.bean.search.HospitalDetailBean;
import com.xiaoxin.guid.util.LoadImageUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalDetailActivity extends BaseActivity {

    private int mHospitalId;
    private ImageView mIvHospitalHead;
    private TextView mTvHospitalName;
    private TextView mTvHospitalAddress;
    private TextView mTvHospitalPhone;
    private TextView mTvHospitalDetail;

    @Override
    public void initData(@Nullable Bundle bundle) {

        mHospitalId = getIntent().getIntExtra("hospitalId",0);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_hospital_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

        TextView title = findViewById(R.id.tv_title_name);
        title.setText("查医院");
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mIvHospitalHead = findViewById(R.id.iv_hospital_head);
        mTvHospitalName = findViewById(R.id.tv_hospital_name);
        mTvHospitalAddress = findViewById(R.id.tv_hospital_address);
        mTvHospitalPhone = findViewById(R.id.tv_hospital_phone);
        mTvHospitalDetail = findViewById(R.id.tv_hospital_detail);

    }

    @Override
    public void doBusiness() {
        loadHospitalList();
    }

    private void loadHospitalList() {
        new Thread(() -> {
            Map<String,String> map = new HashMap<>();
            map.put("id",mHospitalId+"");

            map.put("dxa_entry","event_homepage_sepcial_area_click_查医院");
            map.put("mc","000000006fba5445ffffffff946c9cbf");
            map.put("cn","General");
            map.put("hardName","SM-G955F");
            map.put("ac","d5424fa6-adff-4b0a-8917-4264daf4a348");
            map.put("bv","2017");
            map.put("vc","7.6.7");
            map.put("vs","4.4.2");
            runOnUI(HttpGet.get(Api.HOSPITAL_DETAIL, map));
        }).start();
    }

    /**
     * 更新ui
     * @param result
     */
    private void runOnUI(String result) {
        runOnUiThread(() ->{
            HospitalDetailBean hospitalDetailBean = GsonUtils.GsonToBean(result, HospitalDetailBean.class);

            if (hospitalDetailBean.getData() != null && hospitalDetailBean.getData().getItems() != null) {
                List<HospitalDetailBean.DataBean.ItemsBean> items = hospitalDetailBean.getData().getItems();
                HospitalDetailBean.DataBean.ItemsBean itemsBean = items.get(0);
                mTvHospitalName.setText(itemsBean.getName());
                mTvHospitalAddress.setText(itemsBean.getAddress());
                mTvHospitalPhone.setText(itemsBean.getPhone());
                mTvHospitalDetail.setText(itemsBean.getAbout());
                LoadImageUtil.loadImage(this,itemsBean.getPicture(),mIvHospitalHead);
            }
        });
    }

}
