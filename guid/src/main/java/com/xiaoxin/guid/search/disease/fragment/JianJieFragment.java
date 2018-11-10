package com.xiaoxin.guid.search.disease.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.xiaoxin.guid.R;
import com.xiaoxin.guid.base.BaseFragment;

/**
 * @author: xiaoxin
 * date: 2018/10/27
 * describe:
 * 修改内容:
 */
public class JianJieFragment extends BaseFragment {

    private static final String TAG = "JianJieFragment";

    public static JianJieFragment newInstance(String param1) {

        JianJieFragment fragment = new JianJieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_jianjie;
    }

    @Override
    protected void initView(View view) {

        String content = getArguments().getString(ARG_PARAM1);
        Log.e(TAG, "initView: "+content );
        WebView webView = view.findViewById(R.id.webview);
//        mWebView.loadData(news.getContent(), "text/html", "utf-8");//不能用这种，会有乱码
        webView.loadData(content,"text/html; charset=UTF-8",null);
    }

    @Override
    protected void initData() {

    }

}
