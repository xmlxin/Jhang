package com.xiaoxin.guid.search.hospital;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoxin.guid.GsonUtils;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.base.BaseActivity;
import com.xiaoxin.guid.bean.search.SearchHospitalOneBean;
import com.xiaoxin.guid.util.AppUtil;

/**
 * @author: xiaoxin
 * date: 2018/10/27
 * describe: 查找医院第一步
 * 修改内容:
 */
public class KsSearchHospitalActivity extends BaseActivity {

    private RecyclerView mRvSearchHospital;
    private KsSearchHospitalAdapter mKsSearchHospitalAdapter;
    private SearchHospitalOneBean mHospitalOneBean;

    private void initAdapter() {
        mKsSearchHospitalAdapter = new KsSearchHospitalAdapter(R.layout.item_search_hospital_one,mHospitalOneBean.getData().getItems(),this);
        mRvSearchHospital.setLayoutManager(new LinearLayoutManager(this));
        mRvSearchHospital.setAdapter(mKsSearchHospitalAdapter);

        mKsSearchHospitalAdapter.addHeaderView(getHeadView());
    }

    private View getHeadView() {
        View view = getLayoutInflater().inflate(R.layout.item_search_hospital_head, (ViewGroup) mRvSearchHospital.getParent(), false);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_ks_search_hospital;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

        mRvSearchHospital = findViewById(R.id.rv_search_hospital_ks);
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
        String json = AppUtil.getJson("search_one.json", this);
        mHospitalOneBean = GsonUtils.GsonToBean(json, SearchHospitalOneBean.class);

        initAdapter();

    }
}
