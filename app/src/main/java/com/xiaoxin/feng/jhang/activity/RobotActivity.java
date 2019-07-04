package com.xiaoxin.feng.jhang.activity;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.app.Api;
import com.xiaoxin.feng.jhang.util.HttpGet;
import com.xiaoxin.feng.jhang.util.Json2Str;

public class RobotActivity extends AppCompatActivity {

    private static final String TAG = "RobotActivity";
    private EditText mWebhookUrl,mSendContent,mEtAt;
    private Button mBtSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot);

        mWebhookUrl = findViewById(R.id.et_webhook_url);
        mSendContent = findViewById(R.id.et_send_content);
        mEtAt = findViewById(R.id.et_at);
        mBtSend = findViewById(R.id.bt_send);
        mWebhookUrl.setText(Api.DD_JIQIREN);
        click();
//        HttpGet.post1(url,"{\"msgtype\": \"text\",\"text\": {\"content\": \"应用崩溃了,@1825718XXXX  快去看看是什么回事\"}}");

    }

    private void click() {
        mBtSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url = mWebhookUrl.getText().toString().trim();
                        String content = mSendContent.getText().toString().trim();
                        String at = mEtAt.getText().toString().trim();
                        if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(content)) {
                            Looper.prepare();
                            if (!TextUtils.isEmpty(at)) {
                                String messageStr = Json2Str.messageStr(content, at);
                                Log.e(TAG, "run: "+messageStr);
                                String responseCode = HttpGet.post1(url, Json2Str.messageStr(content, at));
                                Toast.makeText(RobotActivity.this,responseCode,Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "run: "+responseCode );
                            }else {
                                String responseCode = HttpGet.post1(url, Json2Str.messageStr(content));
                                Toast.makeText(RobotActivity.this,responseCode,Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "run: "+responseCode );
                            }
                            Looper.loop();
                        }
                    }
                }).start();
            }
        });
    }
}
