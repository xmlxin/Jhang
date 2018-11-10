package com.xiaoxin.feng.jhang.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.adapter.TextAdapter;
import com.xiaoxin.feng.jhang.adapter.TextWatcherAdapter;
import com.xiaoxin.feng.jhang.app.Constant;
import com.xiaoxin.feng.jhang.util.TextStyUtils;
import com.xiaoxin.feng.jhang.util.TransApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MarsTextActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView rvList;
    private EditText etContent;
    TextAdapter mTextAdapter;
    List<String> mList;
//    private SharedPreferencesUtils wxConfig;
    public static String text;
    public boolean hasOnclick;
    public boolean hasIsEmpty;
    TransApi api;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if(what == 1){
                clipboard(msg.obj.toString());
                etContent.setText(msg.obj.toString());
                etContent.setSelection(msg.obj.toString().length());
                Toast.makeText(MarsTextActivity.this,"复制到粘贴板了",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mars_text);

        etContent = (EditText) findViewById( R.id.et_content);
        rvList = (RecyclerView) findViewById(R.id.rv_list);
        initAdapter();
        etContent.setVisibility(View.VISIBLE);

        etContent.addTextChangedListener(new TextWatcherAdapter(){
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);

                if (TextUtils.isEmpty(editable.toString())) {
                    hasIsEmpty = true;
                }
            }
        });
    }

    private void initAdapter() {
        mList = new ArrayList<>();
        mList.add("横线字");
        mList.add("三角");
        mList.add("叶子");
        mList.add("花藤");
        mList.add("菊花文A");
        mList.add("菊花文B");
        mList.add("竖直");
        mList.add("反字");
        mList.add("u型字");
        mList.add("=͟͟͞͞型字");
        mList.add("翻译成英文");
        mList.add("自定义文本");
        rvList.setLayoutManager(new LinearLayoutManager(this));
        mTextAdapter = new TextAdapter(R.layout.text_recyle_item,mList);
        rvList.setAdapter(mTextAdapter);

        mTextAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (adapter.getData().size()-1 == position){
                    Toast.makeText(MarsTextActivity.this,"暂不支持",Toast.LENGTH_SHORT).show();
                }else {
                    if (!hasOnclick) {
                        text = etContent.getText().toString().trim();
                        hasOnclick = !hasOnclick;
                    }
                    if (hasIsEmpty) {
                        text = etContent.getText().toString().trim();
                        hasIsEmpty = false;
                    }
                    transform(position,text);
                }
            }
        });
    }

    private void transform(int position,final String content) {
        if (position == 10) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TransApi  api = new TransApi(Constant.APP_ID, Constant.SECURITY_KEY);
                        String dst =  (new JSONObject(new JSONObject(api.getTransResult(content, "auto", "en")).getJSONArray("trans_result").get(0).toString())).getString("dst");
                        //从全局池中返回一个message实例，避免多次创建message（如new Message）
                        Message msg =Message.obtain();
                        msg.obj = dst;
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else {
            String styTent = TextStyUtils.styTent(position, content);
            clipboard(styTent);
            etContent.setText(styTent);
            etContent.setSelection(styTent.length());
            Toast.makeText(this,"复制到粘贴板了",Toast.LENGTH_SHORT).show();
        }
//        wxConfig = SharedPreferencesUtils.init(MarsTextActivity.this, Constant.SPCONFIG);
//        wxConfig.putInt(Constant.SPEAK,position);
    }

    private void clipboard(String content) {

        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText("Label", content));
    }


}
