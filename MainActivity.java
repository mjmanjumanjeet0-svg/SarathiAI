package com.sarathiai.app;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.pm.PackageManager;

public class MainActivity extends Activity {
    private WebView web;
    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        if (android.os.Build.VERSION.SDK_INT >= 23 && checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 7);
        web = new WebView(this);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setDatabaseEnabled(true);
        s.setMediaPlaybackRequiresUserGesture(false); s.setAllowFileAccess(true); s.setAllowContentAccess(true);
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient(){
            @Override public void onPermissionRequest(final PermissionRequest r){ runOnUiThread(() -> r.grant(r.getResources())); }
        });
        web.loadUrl("file:///android_asset/index.html");
        setContentView(web);
    }
    @Override public void onBackPressed(){ if(web.canGoBack()) web.goBack(); else super.onBackPressed(); }
}
