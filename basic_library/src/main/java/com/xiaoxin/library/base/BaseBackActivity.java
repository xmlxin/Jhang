package com.xiaoxin.library.base;

import android.os.Bundle;

import com.blankj.swipepanel.SwipePanel;
import com.blankj.utilcode.util.SizeUtils;
import com.xiaoxin.library.R;

/**
 * @author: xiaoxin
 * date: 2019/7/19
 * describe: 侧滑返回 activity
 * 修改内容:
 */
public abstract class BaseBackActivity extends BaseActivity {

    /**
     * 返回true 侧滑返回
     * @return
     */
    public abstract boolean isSwipeBack();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(android.R.id.content).setBackgroundColor(getResources().getColor(R.color.mediumGray));
        initSwipeBack();
    }

    private void initSwipeBack() {
        if (isSwipeBack()) {
            final SwipePanel swipeLayout = new SwipePanel(this);
            swipeLayout.setLeftDrawable(R.drawable.base_back);
            swipeLayout.setLeftEdgeSize(SizeUtils.dp2px(100));
            swipeLayout.wrapView(findViewById(android.R.id.content));
            swipeLayout.setOnFullSwipeListener(new SwipePanel.OnFullSwipeListener() {
                @Override
                public void onFullSwipe(int direction) {
                    swipeLayout.close(direction);
                    finish();
                }
            });
        }
    }


}
