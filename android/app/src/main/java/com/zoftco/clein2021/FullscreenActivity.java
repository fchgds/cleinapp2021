package com.zoftco.clein2021;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import clein2021.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    private static final int FILE_CHOOSER_RESULT_CODE = 123;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        WebView myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setCacheMode (WebSettings.LOAD_NO_CACHE);

//        myWebView.setWebViewClient(new MyWebViewClient());

        myWebView.setWebChromeClient(new WebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
//                    mBar.setVisibility(View.GONE);
                } else {
//                    mBar.setVisibility(View.VISIBLE);
//                    mBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

            //For Android API >= 21 (5.0 OS)
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
//                uploadMessageAboveL = filePathCallback;
                openImageChooserActivity();
                return true;
            }

            private void openImageChooserActivity() {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILE_CHOOSER_RESULT_CODE);
            }

        });


        myWebView.loadUrl("https://app.clein.org/clein2021/");

    }
}


