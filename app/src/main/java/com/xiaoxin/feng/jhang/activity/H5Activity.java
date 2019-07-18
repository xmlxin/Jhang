package com.xiaoxin.feng.jhang.activity;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.databinding.ActivityH5Binding;

public class H5Activity extends AppCompatActivity {

    private ActivityH5Binding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_h5);
        mDataBinding.webView.setWebContentsDebuggingEnabled(true);
        mDataBinding.webView.loadUrl("http://toutiao.iiilab.com/");
    }
}
