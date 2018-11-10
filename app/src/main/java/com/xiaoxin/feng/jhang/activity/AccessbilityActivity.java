package com.xiaoxin.feng.jhang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.adapter.TextWatcherAdapter;
import com.xiaoxin.feng.jhang.app.Constant;
import com.xiaoxin.feng.jhang.util.SharedPreferencesUtils;
import com.xiaoxin.feng.jhang.util.TextMsg;
import com.xiaoxin.feng.jhang.util.TrackerWindowManager;

public class AccessbilityActivity extends AppCompatActivity {

    private static final String TAG = "AccessbilityActivity";
    private EditText mEtSendNumber,mEtContent;
    private CheckBox mCbContent;
    private Button mBtOpen,mBtClose,mBtDhsz,mBtHyh;
    private TrackerWindowManager mWindowManagerUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessbility);

        mEtSendNumber = findViewById(R.id.et_send_number);
        mEtContent = findViewById(R.id.et_content);
        mCbContent = findViewById(R.id.cb_content);
        mBtOpen = findViewById(R.id.bt_open);
        mBtClose = findViewById(R.id.bt_close);
        mBtDhsz = findViewById(R.id.bt_dhsz);
        mBtHyh = findViewById(R.id.bt_hyh);

        mWindowManagerUtil = new TrackerWindowManager(this);

        click();
        //输入次数
        mEtSendNumber.addTextChangedListener(new TextWatcherAdapter(){
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                super.onTextChanged(charSequence, i, i1, i2);
                try {
                    int parseInt = Integer.parseInt(charSequence.toString());
                    SharedPreferencesUtils.
                            init(AccessbilityActivity.this)
                            .putInt(Constant.SEND_NUMBER,parseInt);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        //自定义文本监听
        mEtContent.addTextChangedListener(new TextWatcherAdapter(){
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                super.onTextChanged(charSequence, i, i1, i2);

                SharedPreferencesUtils.
                        init(AccessbilityActivity.this)
                        .putString(Constant.ZDY_CONTENT,charSequence.toString());
            }
        });

        //cb 监听
        mCbContent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e(TAG, "onCheckedChanged: "+isChecked );
                    SharedPreferencesUtils.
                            init(AccessbilityActivity.this)
                            .putBoolean(Constant.CB_CONTENT,isChecked);

            }
        });

    }

    private void click() {

        mBtOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindowManagerUtil.showWindow(AccessbilityActivity.this);
            }
        });

        mBtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mWindowManagerUtil.dissMissWindow(AccessbilityActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mBtDhsz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.
                        init(AccessbilityActivity.this)
                        .putString(Constant.ZDY_CONTENT, TextMsg.wxMsg);
            }
        });
        mBtHyh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.
                        init(AccessbilityActivity.this)
                        .putString(Constant.ZDY_CONTENT, TextMsg.piaoguo);
            }
        });
    }
}
