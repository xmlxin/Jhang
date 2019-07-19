package com.xiaoxin.library.base;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.xiaoxin.library.R;

public abstract class BaseTitleActivity extends BaseBackActivity {
    public abstract CharSequence bindTitle();

    protected boolean           isSupportScroll = true;
    protected CoordinatorLayout baseTitleRootLayout;
    protected Toolbar baseTitleToolbar;
    protected FrameLayout baseTitleContentView;
    protected ViewStub mViewStub;

    @Override
    public boolean isSwipeBack() {
        return true;
    }

    @SuppressLint("ResourceType")
    @Override
    public void setRootLayout(@LayoutRes int layoutId) {
        super.setRootLayout(R.layout.common_activity_title);

        this.getWindow()
                .getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        baseTitleRootLayout = findViewById(R.id.baseTitleRootLayout);
        baseTitleToolbar = findViewById(R.id.baseTitleToolbar);
        if (layoutId > 0) {
            if (isSupportScroll) {
                mViewStub = findViewById(R.id.baseTitleStubScroll);
            } else {
                mViewStub = findViewById(R.id.baseTitleStubNoScroll);
            }
            mViewStub.setVisibility(View.VISIBLE);
            baseTitleContentView = findViewById(R.id.commonTitleContentView);
            LayoutInflater.from(this).inflate(layoutId, baseTitleContentView);
        }
        setTitleBar();
        BarUtils.setStatusBarColor(this, ColorUtils.getColor(R.color.white));
        BarUtils.addMarginTopEqualStatusBarHeight(baseTitleRootLayout);
    }

    private void setTitleBar() {
        setSupportActionBar(baseTitleToolbar);
        ActionBar titleBar = getSupportActionBar();
        if (titleBar != null) {
            titleBar.setDisplayHomeAsUpEnabled(true);
            titleBar.setTitle(bindTitle());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
