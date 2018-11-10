package com.xiaoxin.feng.jhang;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiaoxin.feng.jhang.activity.AccessbilityActivity;
import com.xiaoxin.feng.jhang.activity.ApkParserActivity;
import com.xiaoxin.feng.jhang.activity.DouyinPersonActivity;
import com.xiaoxin.feng.jhang.activity.MarsTextActivity;
import com.xiaoxin.feng.jhang.activity.ReadHardActivity;
import com.xiaoxin.feng.jhang.activity.ReadPicActivity;
import com.xiaoxin.feng.jhang.activity.RobotActivity;
import com.xiaoxin.feng.jhang.activity.SmsBackActivity;
import com.xiaoxin.feng.jhang.activity.WechatZbActivity;
import com.xiaoxin.feng.jhang.activity.animation.AnimationActivity;
import com.xiaoxin.feng.jhang.adapter.FunctionAdapter;
import com.xiaoxin.feng.jhang.bean.FunctionBean;
import com.xiaoxin.feng.jhang.util.AccessibilityUtil;
import com.xiaoxin.feng.jhang.wallpaper.WallPaperActivity;
import com.xiaoxin.guid.activity.HumanBodyActivity;
import com.xiaoxin.guid.search.disease.SearchDiseaseActivity;
import com.xiaoxin.guid.search.drug.DrugListActivity;
import com.xiaoxin.guid.search.hospital.KsSearchHospitalActivity;
import com.xiaoxin.guid.search.truth.TruthActivity;
import com.xiaoxin.guid.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mReadPic, mReadHard, mReadWallPaper,mMarsText,mAutoMessage,
                    mBtDdRobot,bt_wechat_zb,mApkParser,mBtSmsBack,mBtDz,mBtDouyin,
                    mBtAnimation,mBtSearchDrug,mBtSearchHospital,mBtOnlineDrug,mBtFjDrug,
                    mBtDown,mTvTruth;
    private RecyclerView mRvList;
    private FunctionAdapter mFunctionAdapter;
    private List<FunctionBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        mRvList = (RecyclerView)findViewById(R.id.rv_list);

        mReadPic = (TextView) findViewById(R.id.bt_read_pic);
        mApkParser = (TextView) findViewById(R.id.bt_apk_parser);
        mReadHard = (TextView) findViewById(R.id.bt_read_hard);
        mReadWallPaper = (TextView) findViewById(R.id.bt_wall_paper);
        mMarsText = (TextView) findViewById(R.id.bt_mars_text);
        mAutoMessage = (TextView) findViewById(R.id.bt_auto_message);
        mBtDdRobot = (TextView) findViewById(R.id.bt_dd_robot);
        bt_wechat_zb = (TextView) findViewById(R.id.bt_wechat_zb);
        mBtSmsBack = (TextView) findViewById(R.id.bt_sms);
        mBtDz = (TextView) findViewById(R.id.bt_dz);
        mBtDouyin = (TextView) findViewById(R.id.bt_douyin);
        mBtAnimation = (TextView) findViewById(R.id.bt_animation);
        mBtSearchDrug = (TextView) findViewById(R.id.bt_search_drug);
        mBtSearchHospital = (TextView) findViewById(R.id.bt_search_hospital);
        mBtOnlineDrug = (TextView) findViewById(R.id.bt_online_drug);
        mBtFjDrug = (TextView) findViewById(R.id.tv_fj_drug);
        mBtDown = (TextView) findViewById(R.id.bt_down);
        mTvTruth = (TextView) findViewById(R.id.tv_truth);


        click();
        checkPermission();

        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add(new FunctionBean("读取图片",1));
        }
        mRvList.setLayoutManager(new GridLayoutManager(this,4));
        mFunctionAdapter = new FunctionAdapter(R.layout.function_recyle_item,mList);
        mRvList.setAdapter(mFunctionAdapter);

        new MaterialTapTargetPrompt.Builder(this)
                .setTarget(mApkParser)
                .setPrimaryText("提示")
                .setSecondaryText("选择apk文件可以读取清单信息")
                .setPromptBackground(new RectanglePromptBackground())
                .setPromptFocal(new RectanglePromptFocal())
                .show();

        //design包中 底部弹出效果
//        BottomSheetDialog sheetDialog = new BottomSheetDialog();
//        sheetDialog.setContentView(view);
//        sheetDialog.show();

    }

    private void click() {

        mReadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReadPicActivity.class));
            }
        });

        mReadHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReadHardActivity.class));
            }
        });

        mReadWallPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WallPaperActivity.class));
            }
        });

        mMarsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MarsTextActivity.class));
            }
        });

        mAutoMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean accessibility = AccessibilityUtil.isAccessibilitySettingsOn(MainActivity.this);
                if (!accessibility) {
                    showDialog();
                }else {
                    startActivity(new Intent(MainActivity.this,AccessbilityActivity.class));
                }
            }
        });
        mBtDdRobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RobotActivity.class));
            }
        });

        bt_wechat_zb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WechatZbActivity.class));
            }
        });

        mApkParser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ApkParserActivity.class));
//                startActivity(new Intent(MainActivity.this,AnimationMainActivity.class));
//                startActivity(new Intent(MainActivity.this,H5Activity.class));
            }
        });
        mBtSmsBack.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,SmsBackActivity.class));
        });
        mBtDz.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HumanBodyActivity.class));
        });

        mBtDouyin.setOnClickListener(v ->{
            startActivity(new Intent(MainActivity.this, DouyinPersonActivity.class));
        });
        mBtAnimation.setOnClickListener(v ->{
            startActivity(new Intent(MainActivity.this, AnimationActivity.class));
        });
        mBtSearchDrug.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SearchDiseaseActivity.class));
        });
        mBtSearchHospital.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, KsSearchHospitalActivity.class));
        });
        mBtOnlineDrug.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, DrugListActivity.class));
        });
        mBtFjDrug.setOnClickListener(v -> {
            AppUtil.startMap(this);
//            startActivity(new Intent(MainActivity.this, Test1Activity.class));
        });
        mBtDown.setOnClickListener(v -> {
            Uri uri = Uri.parse("http://toutiao.iiilab.com/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        mTvTruth.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TruthActivity.class));
        });
    }

    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("需要开启辅助功能")
                .setMessage("使用此功能需要开启辅助功能。现在去开启辅助功能")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AccessibilityUtil.checkAccessibility(MainActivity.this);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                       Toast.makeText(MainActivity.this,"好吧(ಥ﹏ಥ)",Toast.LENGTH_SHORT).show();
                    }
                }).show().show();
    }

    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_SMS,
                            Manifest.permission.WRITE_CONTACTS,
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.SYSTEM_ALERT_WINDOW,
                            Manifest.permission.ACCESS_FINE_LOCATION}, 60);
        }
    }

}
