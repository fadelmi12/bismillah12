package com.example.bismillah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class activityLms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lms);
        WebView web = (WebView) findViewById(R.id.lmsView);
        web.loadUrl("http://lms.student.pnm.ac.id/login");
        web.setWebViewClient(new WebViewClient());
    }
}