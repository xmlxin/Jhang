package com.xiaoxin.feng.jhang.activity.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.widget.ShareButtonView;

public class ShareButtonActivity extends AppCompatActivity {

    private ShareButtonView mShareButtonView;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_button);

        mShareButtonView = (ShareButtonView) findViewById(R.id.share_button);
        mShareButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count % 2 == 0)
                    mShareButtonView.reset();
                else
                    mShareButtonView.startAnimation();
                count++;
            }
        });
    }
}
