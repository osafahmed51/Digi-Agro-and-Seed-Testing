package com.example.digiagro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webView1 extends AppCompatActivity {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view1);
        webview = findViewById(R.id.web);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://www.youtube.com");

    }
}