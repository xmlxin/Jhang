package com.xiaoxin.guid.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.xiaoxin.guid.Api;
import com.xiaoxin.guid.GsonUtils;
import com.xiaoxin.guid.HttpGet;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.BwBean;
import com.xiaoxin.guid.bean.GuidanceBean;
import com.xiaoxin.guid.databinding.ActivityHumanBodyBinding;
import com.xiaoxin.guid.db.Dbdao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiaoxin
 * date: 2018/10/10
 * describe:人体部位图
 * 修改内容:
 */
public class HumanBodyActivity extends AppCompatActivity {

    private static final String TAG = "HumanBodyActivity";
    ActivityHumanBodyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_human_body);

        Dbdao.copyAddressDB(this,"guidance.db");

        binding.btMan.setOnClickListener(v -> {
            startActivity(new Intent(this,GuidanceActivity.class)
                            .putExtra("sex","m"));
//            loadBwzz("m");
        });

        binding.btWoman.setOnClickListener(v -> {
            startActivity(new Intent(this,GuidanceActivity.class)
                    .putExtra("sex","w"));
//            loadBwzz("w");
        });

        binding.btChild.setOnClickListener(v -> {
            startActivity(new Intent(this,GuidanceActivity.class)
                    .putExtra("sex","c"));
//            loadBwzz("c");
        });

//        loadBz();

    }

    /**
     * 请求对应部位症状列表的具体一项病症
     */
    private void loadBz() {
        SQLiteDatabase db = null;
        db = SQLiteDatabase.openDatabase(Dbdao.PATH, null, SQLiteDatabase.OPEN_READWRITE);
        new Thread(() -> {

            ArrayList<GuidanceBean> guidanceBeanArrayList = Dbdao.querySymptom();
            Log.e(TAG, "onCreate: "+guidanceBeanArrayList.size());
            for (int i = 0; i < guidanceBeanArrayList.size(); i++) {
                GuidanceBean guidanceBean = guidanceBeanArrayList.get(i);
                Map<String,String> map = new HashMap<>();
                map.put("sex",guidanceBean.getSex());
                map.put("symptomId",guidanceBean.getId());
                String result = HttpGet.get(Api.SECOND, map);
                if (!TextUtils.isEmpty(result)) {
                    BwBean bwBean = GsonUtils.GsonToBean(result, BwBean.class);
                    Dbdao.insertDisease(bwBean.getData(),guidanceBean.getSex(),guidanceBean.getPart(),guidanceBean.getId());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.e(TAG, "loadBz: 数据加载完毕" );
        }).start();
    }

    /**
     * 请求对应部位症状列表:第一步
     * @param sex
     */
    private void loadBwzz(String sex) {
        String[] stringArray;
        if ("w".equals(sex)) {
            stringArray = getResources().getStringArray(R.array.woman);
        }else {
            stringArray = getResources().getStringArray(R.array.man);
        }

        Log.e(TAG, "loadBwzz: stringArray"+stringArray.length );

        for (int i = 0; i < stringArray.length; i++) {
            Log.e(TAG, "loadBwzz: stringArray"+stringArray[i] );
        }
        new Thread(() -> {
            for (int i = 0; i < stringArray.length; i++) {
                Map<String,String> map = new HashMap<>();
                map.put("sex",sex);
                map.put("bodyPartName",stringArray[i]);

                String result = HttpGet.get(Api.FIRST, map);
                if (!TextUtils.isEmpty(result)) {
                    BwBean bwBean = GsonUtils.GsonToBean(result,BwBean.class);
//                    Dbdao.insertSymptom(bwBean.getData(),sex,stringArray[i]);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.e(TAG, "loadBwzz: 数据加载完成" );
        }).start();

    }

}
