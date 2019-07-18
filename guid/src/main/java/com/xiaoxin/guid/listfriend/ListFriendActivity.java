package com.xiaoxin.guid.listfriend;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaoxin.guid.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ListFriendActivity extends AppCompatActivity {

    private TextView tv_sure;
    private RecyclerView mRecyclerView;
    private List<DepartmentBean> mBeanList;

    private ArrayList<MultiItemEntity> mMultiItemEntities;
    private DepartAdapter mListMulAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_friend);

        tv_sure = findViewById(R.id.tv_sure);
        mRecyclerView = findViewById(R.id.rv_list);


        mMultiItemEntities = new ArrayList<>();
        mBeanList = new ArrayList<>();

        String json = getJson("list.json", this);
//        mBeanList = GsonUtils.GsonToList(json, DepartmentBean.class);

        mBeanList = new Gson().fromJson(json, new TypeToken<List<DepartmentBean>>(){}.getType());
        for (int i = 0; i < mBeanList.size(); i++) {
            DepartmentBean listBean = mBeanList.get(i);
            DepartmentBean.KcHrDeptBean kcHrDept = listBean.kcHrDept;
            listBean.kcHrDept.number = listBean.kcHrEmployes.size();

//            mMultiItemEntities.add(listBean.kcHrDept);
            for (int j = 0; j < listBean.kcHrEmployes.size(); j++) {
                DepartmentBean.KcHrEmployesBean kcHrEmployesBean = listBean.kcHrEmployes.get(j);
                kcHrDept.addSubItem(kcHrEmployesBean);
            }

            mMultiItemEntities.add(kcHrDept);
        }

        initAdapter();
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder deptId = new StringBuilder();
                StringBuilder employeId = new StringBuilder();
                StringBuilder employeName = new StringBuilder();
                for (int i = 0; i < mBeanList.size(); i++) {
                    if (mBeanList.get(i).kcHrDept.isSelect()) {
                        deptId.append(mBeanList.get(i).kcHrDept.deptId+",");
                        employeName.append(mBeanList.get(i).kcHrDept.deptName+",");
                    }else {
                        for (int j = 0; j < mBeanList.get(i).kcHrEmployes.size(); j++) {
                            DepartmentBean.KcHrEmployesBean kcHrEmployesBean = mBeanList.get(i).kcHrEmployes.get(j);
                            if (kcHrEmployesBean.isSelect()) {
                                employeId.append(kcHrEmployesBean.employeId+",");
                                employeName.append(kcHrEmployesBean.employeName+",");
                            }
                        }
                    }
                }
                Log.e("xiaoxin", "deptId: " +deptId.toString());
                Log.e("xiaoxin", "employeId: " +employeId.toString());
                Log.e("xiaoxin", "employeName: " +employeName.toString());
            }
        });

    }

    private void initAdapter() {

        mListMulAdapter = new DepartAdapter(mMultiItemEntities,this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mListMulAdapter);

    }

    /**
     * 读取asset下json文件
     * @param fileName
     * @param context
     * @return
     */
    public static String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

//    private ArrayList<MultiItemEntity> generateData() {
//        int lv0Count = 9;
//        int lv1Count = 3;
//
//        String[] nameList = {"Bob", "Andy", "Lily", "Brown", "Bruce"};
//        Random random = new Random();
//
//        ArrayList<MultiItemEntity> res = new ArrayList<>();
//        for (int i = 0; i < lv0Count; i++) {
//            Level0Item lv0 = new Level0Item("This is " + i + "th item in Level 0", "subtitle of " + i);
//            for (int j = 0; j < lv1Count; j++) {
//                Level1Item lv1 = new Level1Item("Level 1 item: " + j, "(no animation)");
//
//                lv0.addSubItem(lv1);
//            }
//            res.add(lv0);
//        }
//
//        return res;
//    }

}
