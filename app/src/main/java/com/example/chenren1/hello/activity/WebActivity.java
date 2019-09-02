package com.example.chenren1.hello.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.chenren1.hello.R;
import com.example.chenren1.hello.utils.LogUtils;

/**
 * Created by chenren1 on 2018/5/21.
 */

public class WebActivity extends Activity {

    private WebView mWebView;
    private ViewGroup mContainer;
    private View mVideoView;

    private String url = "http://m.weibo.cn/status/HBraR56yH";
    private boolean isLandscape =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mContainer = findViewById(R.id.container);

        initWebView();
    }

    private void initWebView() {
        mWebView = findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);
                LogUtils.d("onShowCustomView ");
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                isLandscape = true;
                mVideoView = view;
                mContainer.addView(view);
                mContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onHideCustomView() {
                super.onHideCustomView();
                LogUtils.d("onHideCustomView ");
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                isLandscape = false;
                mContainer.removeView(mVideoView);
                mContainer.setVisibility(View.VISIBLE);
                mVideoView = null;
            }
        });
        mWebView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtils.d("onKeyDown " + keyCode);

        if (isLandscape){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            isLandscape = false;
            return true;
        }

        LogUtils.d("onKeyDown WebView canGoBack:" + mWebView.canGoBack());
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtils.d("onConfigurationChanged ");
        super.onConfigurationChanged(newConfig);
    }
}
