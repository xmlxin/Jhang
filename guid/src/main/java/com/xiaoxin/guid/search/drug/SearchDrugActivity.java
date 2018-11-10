package com.xiaoxin.guid.search.drug;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoxin.guid.Api;
import com.xiaoxin.guid.GsonUtils;
import com.xiaoxin.guid.HttpGet;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.base.BaseActivity;
import com.xiaoxin.guid.bean.search.SearchResultBean;
import com.xiaoxin.guid.util.KeyboardUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchDrugActivity extends BaseActivity {

    private static final String TAG = "SearchDrugActivity";
    private FrameLayout mCommonContainer;
    private AutoNewLineLayout mAutoNewLineLayout;

    String[] tags = {"布洛芬", "头炮" , "三九感冒灵", "阿司匹林", "加合百服宁",  "达吉",
            "红霉素软膏", "白霉素软膏" , "达喜","黄霉素软膏", "板蓝根", "九九感冒林", "口服液"};
    private View mHistory,mSearchList;
    private EditText mEtSearch;
    private ImageView mClear;
    private RecyclerView rvSearchList;
    
    private SearchResultAdapter mSearchResultAdapter;


    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_search_drug;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

        mCommonContainer = findViewById(R.id.rl_content);
        mEtSearch = findViewById(R.id.et_search);
        mClear = findViewById(R.id.iv_clear);
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtSearch.setText("");
            }
        });
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mHistory = View.inflate(this, R.layout.layout_search_history, null);
        mAutoNewLineLayout = mHistory.findViewById(R.id.auto_layout);
    }

    @Override
    public void doBusiness() {

        changeViewTo(mHistory);
        addTags();

        searchList();
        etListener();
    }

    private void addTags() {
        for (int i = 0; i < tags.length; i++) {
            TextView tv = new TextView(this);
            tv.setBackgroundResource(R.drawable.shape_history_bg);
            tv.setText(tags[i]);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEtSearch.setText(tv.getText().toString());
//                    Toast.makeText(SearchDrugActivity.this,tv.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
            mAutoNewLineLayout.addView(tv);
        }
    }

    /**
     * 切换到目标界面，原有的会被删除
     * @param view
     */
    public void changeViewTo(View view){
        mCommonContainer.removeAllViews();
        mCommonContainer.addView(view);
    }

    private void etListener() {

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e(TAG, "afterTextChanged: " );
                if (TextUtils.isEmpty(s.toString())) {
                    mClear.setVisibility(View.GONE);
                    changeViewTo(mHistory);
                    KeyboardUtils.showSoftInput(SearchDrugActivity.this);
                }else {
                    mClear.setVisibility(View.VISIBLE);
                    loadDrugList(s.toString().trim());
                    changeViewTo(mSearchList);
                }
                mEtSearch.setSelection(s.toString().length());
            }
        });
        //搜索按钮监听
        mEtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    // 先隐藏键盘
                    KeyboardUtils.hideSoftInput(SearchDrugActivity.this);
                    changeViewTo(mSearchList);
                    loadDrugList(mEtSearch.getText().toString().trim());
                }
                return false;
            }
        });
    }
    
    
    private void searchList() {
        mSearchList = View.inflate(this, R.layout.layout_search_list, null);
        rvSearchList = mSearchList.findViewById(R.id.rv_search_list);
        initAdapter();
    }

    private void initAdapter() {
        List<String> mList = new ArrayList<>();
        mSearchResultAdapter = new SearchResultAdapter(R.layout.item_search_list_item,mList);
        rvSearchList.setLayoutManager(new LinearLayoutManager(this));
        rvSearchList.setAdapter(mSearchResultAdapter);
    }

    private void loadDrugList(String keyword) {
        new Thread(() -> {
            Map<String,String> map = new HashMap<>();
            map.put("keyword",keyword);
            map.put("dxa_entry","event_drug_ask_select");
            map.put("mc","000000006fba5445ffffffff946c9cbf");
            map.put("cn","General");
            map.put("hardName","SM-G955F");
            map.put("ac","d5424fa6-adff-4b0a-8917-4264daf4a348");
            map.put("bv","2017");
            map.put("vc","7.6.7");
            map.put("vs","4.4.2");
            runOnUI(HttpGet.get(Api.SEARCH_DRUG_LIST, map));
        }).start();
    }

    /**
     * 更新ui
     * @param result
     */
    private void runOnUI(String result) {
        runOnUiThread(() ->{
            SearchResultBean searchResultBean = GsonUtils.GsonToBean(result, SearchResultBean.class);
            if (searchResultBean.getData()!= null && searchResultBean.getData().getItems() != null) {
                mSearchResultAdapter.setNewData(searchResultBean.getData().getItems());
            }

        });
    }
}
