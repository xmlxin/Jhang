package com.xiaoxin.guid.search.drug;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaoxin.guid.Api;
import com.xiaoxin.guid.GsonUtils;
import com.xiaoxin.guid.HttpGet;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.search.DrugDetailBean;
import com.xiaoxin.guid.bean.search.DrugTypeBean;
import com.xiaoxin.guid.db.SearchHospitalDao;
import com.xiaoxin.guid.util.AppUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.jianshu.com/p/a9f2174b7714
 */
public class DrugListActivity extends AppCompatActivity {

    private static final String TAG = "DrugListActivity";
    private DrugListAdapter mDrugListAdapter;
    private DrugListDetailAdapter mDrugListDetailAdapter;
    private RecyclerView mRvDrugType;
    private RecyclerView mRvDrugDetail;
    private DrugTypeBean mDrugTypeBean;

    private List<DrugDetailBean.DataBean.ItemsBean> mDrugListDetail;

    private int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow()
                .getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_drug_list);

        setTitle("在线选药");

        mRvDrugType = findViewById(R.id.rv_drug_type);
        mRvDrugDetail = findViewById(R.id.rv_drug_detail);
        TextView search = findViewById(R.id.tv_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrugListActivity.this,SearchDrugActivity.class));
            }
        });
        String json = AppUtil.getJson("drug_type.json", this);

        mDrugTypeBean = GsonUtils.GsonToBean(json, DrugTypeBean.class);

        initAdapter();

//        search();
    }

    private void search() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = AppUtil.getJson("drug_type.json", DrugListActivity.this);

                DrugTypeBean mDrugTypeBean1 = GsonUtils.GsonToBean(json, DrugTypeBean.class);
                List<DrugTypeBean.DataBean.ItemsBean> items = mDrugTypeBean1.getData().getItems();
                for (int i = 0; i < items.size(); i++) {
                    Map<String,String> map = new HashMap<>();
                    map.put("category_id",items.get(i).getId());
                    map.put("page_index",index+"");
                    map.put("items_per_page","1000");
                    map.put("dxa_entry","event_homepage_top_click_在线购药");
                    map.put("mc","000000006fba5445ffffffff946c9cbf");
                    map.put("cn","General");
                    map.put("hardName","SM-G955F");
                    map.put("ac","d5424fa6-adff-4b0a-8917-4264daf4a348");
                    map.put("bv","2017");
                    map.put("vc","7.6.7");
                    map.put("vs","4.4.2");
                    String result = HttpGet.get(Api.SEARCH_DRUG, map);
                    DrugDetailBean drugDetailBean = GsonUtils.GsonToBean(result, DrugDetailBean.class);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SearchHospitalDao.insertDrug(drugDetailBean.getData().getItems(),items.get(i).getName());
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.e(TAG, "loadBz: 数据加载完毕" );
            }

        }).start();
    }

    private void initAdapter() {


        mDrugListAdapter = new DrugListAdapter(R.layout.item_ks_first,mDrugTypeBean.getData().getItems(),this);
        mRvDrugType.setLayoutManager(new LinearLayoutManager(this));
        mRvDrugType.setAdapter(mDrugListAdapter);

        mDrugListAdapter.setSize(mDrugTypeBean.getData().getItems().size());
        mDrugListAdapter.setItemChecked(0);

        loadDrugList(mDrugTypeBean.getData().getItems().get(0).getId());

        mDrugListDetail = new ArrayList<>();
        mDrugListDetailAdapter = new DrugListDetailAdapter(R.layout.item_drug_list,mDrugListDetail,this);
        mRvDrugDetail.setLayoutManager(new LinearLayoutManager(this));
        mRvDrugDetail.setAdapter(mDrugListDetailAdapter);

        mDrugListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDrugListAdapter.setItemChecked(position);

                DrugTypeBean.DataBean.ItemsBean bean = (DrugTypeBean.DataBean.ItemsBean)
                                                    adapter.getData().get(position);
                loadDrugList(bean.getId());
            }
        });

        mDrugListDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                DrugDetailBean.DataBean.ItemsBean bean = (DrugDetailBean.DataBean.ItemsBean)
                                                        adapter.getData().get(position);
                Intent intent = new Intent(DrugListActivity.this,DrugDetailActivity.class);
                intent.putExtra("bean",bean);
                startActivity(intent);
                overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_silent);

            }
        });

//        mDrugListDetailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//
//            private TextView mTvCount;
//            private ImageView mCutDrug;
//            private ImageView mAddDrug;
//
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                mTvCount = view.findViewById(R.id.tv_count);
//                mCutDrug = view.findViewById(R.id.iv_cut_drug);
//                mAddDrug = view.findViewById(R.id.iv_add_drug);
//                if (view.getId() == R.id.iv_add_drug) {
//                    Toast.makeText(DrugListActivity.this,"点击了",Toast.LENGTH_SHORT).show();
//                    mAddDrug.setOnClickListener(v -> {
//                        ViewAnimator.animate(mCutDrug)
//                                .translationX(mAddDrug.getLeft() - mCutDrug.getLeft(), 0)
//                                .rotation(360)
//                                .alpha(0, 255)
//                                .duration(300)
//                                .interpolator(new DecelerateInterpolator())
//                                .andAnimate(mTvCount)
//                                .translationX(mAddDrug.getLeft() - mTvCount.getLeft(), 0)
//                                .rotation(360)
//                                .alpha(0, 255)
//                                .interpolator(new DecelerateInterpolator())
//                                .duration(300)
//                                .start();
//                    });
//                }else if (view.getId() == R.id.iv_cut_drug) {
//                    mCutDrug.setOnClickListener(v -> {
//                        ViewAnimator.animate(mCutDrug)
//                                .translationX(0, mAddDrug.getLeft() - mCutDrug.getLeft())
//                                .rotation(-360)
//                                .alpha(255, 0)
//                                .duration(300)
//                                .interpolator(new AccelerateInterpolator())
//                                .andAnimate(mTvCount)
//                                .onStop(new AnimationListener.Stop() {
//                                    @Override
//                                    public void onStop() {
////                        mButton.expendAnim();
////                        if (circle_anim) {
////                        }
//                                    }
//                                })
//                                .translationX(0, mAddDrug.getLeft() - mTvCount.getLeft())
//                                .rotation(-360)
//                                .alpha(255, 0)
//                                .interpolator(new AccelerateInterpolator())
//                                .duration(300)
//                                .start();
//                    });
//                }
//            }
//        });
    }

    private void loadDrugList(String id) {
        new Thread(() -> {
            Map<String,String> map = new HashMap<>();
            map.put("category_id",id);
            map.put("page_index",index+"");
            map.put("items_per_page","100");

            map.put("dxa_entry","event_homepage_top_click_在线购药");
            map.put("mc","000000006fba5445ffffffff946c9cbf");
            map.put("cn","General");
            map.put("hardName","SM-G955F");
            map.put("ac","d5424fa6-adff-4b0a-8917-4264daf4a348");
            map.put("bv","2017");
            map.put("vc","7.6.7");
            map.put("vs","4.4.2");
//            String result = HttpGet.get(Api.SEARCH_DISEASE, map);
            runOnUI(HttpGet.get(Api.SEARCH_DRUG, map));
        }).start();
    }

    /**
     * 更新ui
     * @param result
     */
    private void runOnUI(String result) {
        runOnUiThread(() ->{
            DrugDetailBean drugDetailBean = GsonUtils.GsonToBean(result, DrugDetailBean.class);

            mDrugListDetailAdapter.setNewData(drugDetailBean.getData().getItems());

        });
    }
}
