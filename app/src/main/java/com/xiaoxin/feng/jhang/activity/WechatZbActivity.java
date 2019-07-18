package com.xiaoxin.feng.jhang.activity;

import android.content.Intent;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.bean.ImagePiece;
import com.xiaoxin.feng.jhang.util.BitmapUtil;
import com.xiaoxin.feng.jhang.util.HttpPost;
import com.xiaoxin.feng.jhang.util.ShareUtil;
import com.xiaoxin.feng.jhang.util.UriPathUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WechatZbActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "WechatZbActivity";
    private static final int FILE_SELECT_CODE = 0;
    private Button mSelectFile,mShareWechat,mShareWechatContent;
    private TextView mResult;
    private String mPath;
    private  ArrayList<File> fileArrayList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_zb);
        mSelectFile = (Button) findViewById(R.id.select_file);
        mShareWechat = (Button) findViewById(R.id.share_wechat);
        mResult = (TextView) findViewById(R.id.tv_result);
        mShareWechatContent = (Button) findViewById(R.id.share_wechat_content);

        mSelectFile.setOnClickListener(this);
        mShareWechat.setOnClickListener(this);
        mResult.setOnClickListener(this);
        mShareWechatContent.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.select_file:
                selectPhoto();
                break;
            case R.id.share_wechat:
                ShareUtil.originalShareImage(this,fileArrayList);
                break;
            case R.id.tv_result:
                break;
            case R.id.share_wechat_content:
                ShareUtil.originalShareText(this,"ni好我要装备");
                break;
        }
    }

    private void postFile() {
        Map<String, String> map = new HashMap<>();
        Map<String, File> fileMap = new HashMap<>();
        File file = new File(mPath);
        fileMap.put(file.getName(),file);
        HttpPost.postRequest("http://192.168.1.116:8080/FileUploadServlet",map,fileMap);
    }

    /**
     * 查看文件
     */
    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*");
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // Uri fileUri = FileProvider7.getUriForFile(this);
        try {
            startActivityForResult( Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    Log.e(TAG, "onActivityResult: " );
                    Toast.makeText(WechatZbActivity.this,"装逼中...",Toast.LENGTH_SHORT).show();
                    try {
                        mPath = UriPathUtil.getPath(this,data.getData());
                        postFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "run: 开始画画" );

                            List<ImagePiece> split = BitmapUtil.
                                    split( BitmapUtil.decodeFile2Bitmap(mPath), 3, 3);

                            Log.e(TAG, "run: "+split.size() );
                            for (int i = 0; i < split.size(); i++) {
                                File file = BitmapUtil.savePic(split.get(i).bitmap, split.get(i).index);
                                fileArrayList.add(file);
                            }
                            Looper.prepare();
                            Toast.makeText(WechatZbActivity.this,"装逼完成",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }).start();
                }
                break;
        }


    }
}
