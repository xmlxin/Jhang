package com.xiaoxin.feng.jhang.activity.animation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.widget.AutoNewLineLayout;

public class AutoLineLayoutActivity extends AppCompatActivity {

    AutoNewLineLayout autoNewLineLayout;
    String[] tags = {"诛仙", "青云志" , "老九门", "花千骨", "琅琊榜", "伪装者", "仙剑奇侠传",
            "诛仙", "青云志" , "老九门", "花千骨", "琅琊榜", "伪装者", "仙剑奇侠传仙剑奇侠传仙剑奇侠传仙剑奇侠传仙剑奇侠传仙剑奇侠传"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_line_layout);

        autoNewLineLayout = (AutoNewLineLayout) findViewById(R.id.anl_tags);
        addTags();
    }

    private void addTags() {
        for (int i = 0; i < tags.length; i++) {
            TextView tv = new TextView(this);
            tv.setBackgroundResource(R.drawable.radius_border_teal_solod_white);
            tv.setText(tags[i]);
            autoNewLineLayout.addView(tv);
        }
    }
}
