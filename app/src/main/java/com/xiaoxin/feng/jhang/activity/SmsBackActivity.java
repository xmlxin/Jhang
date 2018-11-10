package com.xiaoxin.feng.jhang.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xiaoxin.feng.jhang.MainActivity;
import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.app.BaseBindingActivity;
import com.xiaoxin.feng.jhang.databinding.ActivitySmsbackBinding;
import com.xiaoxin.feng.jhang.util.SmsTools;

/**
 * @author: xiaoxin
 * date: 2018/10/8
 * describe: 短信备份--恢复
 * 修改内容:
 */
public class SmsBackActivity extends BaseBindingActivity<ActivitySmsbackBinding> {

    private static final String TAG = "SmsBackActivity";

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_smsback;
    }

    @Override
    public void bindingView() {
       mBinding.btSmsBack.setOnClickListener(v -> {
           SmsTools.backUpSms(SmsBackActivity.this, new SmsTools.BackupCallback() {
               @Override
               public void beforeSmsBackup(int max) {
                   Log.e(TAG, "beforeSmsBackup: "+max );
               }

               @Override
               public void onSmsBackup(int progress) {
                   Log.e(TAG, "onSmsBackup: "+progress );
               }
           });
       });
    }


    @Override
    public void doBusiness() {

    }
}
