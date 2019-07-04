package com.xiaoxin.jhang.qqun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoxin.library.HttpGet;
import com.xiaoxin.library.base.BaseActivity;
import com.xiaoxin.library.util.GsonUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * @author wfx
 * @date 2019/4/15 9:52
 * @desc
 */
public class QqunActivity extends BaseActivity {

    private static final String TAG = "QqunActivity";

    private EditText mEtQhao;
    private Button mBtSearch;
    private RecyclerView mRvList;

    @Override
    public void initData(@Nullable Bundle bundle) {


    }

    private void search() {
        String searchText = mEtQhao.getText().toString().trim();
        if (TextUtils.isEmpty(searchText)) {
            Toast.makeText(this,"请输入内容",Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(() -> {
            Map<String,String> map = new HashMap<>();

            map.put("bv","2017");
            map.put("vc","7.6.7");
            map.put("vs","4.4.2");
//            String result = HttpGet.get(Api.SEARCH_DISEASE, map);
//            Log.e(TAG, "loadDiseaseInfo: "+result );
            runOnUI(HttpGet.get(Api.QUN_LIST, map));
        }).start();
    }

    /**
     * 更新ui
     * @param result
     */
    private void runOnUI(String result) {
        Log.e(TAG, "runOnUI: "+result );
//        runOnUiThread(() ->{
//            mDetailBean = GsonUtils.GsonToBean(result, DiseaseDetailBean.class);
//
//        });
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_qqun;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

        mEtQhao = findViewById(R.id.et_qq_hao);
        mBtSearch = findViewById(R.id.bt_search);
        mRvList = findViewById(R.id.rv_qun_list);
    }

    @Override
    public void doBusiness() {
        mBtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }
}
