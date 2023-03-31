package com.example.digiagro;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Bundle;

public class webView extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webview = findViewById(R.id.web);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://www.google.com");

    }
}