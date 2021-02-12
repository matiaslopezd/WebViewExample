package com.example.webviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.view.View;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.ConsoleMessage;

public class MainActivity extends AppCompatActivity {

    /**
     * Thanks to James McCracken - https://stackoverflow.com/a/34343101
     */

    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = { android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.CAMERA};
    private static final String TAG = MainActivity.class.getSimpleName();
    private PermissionRequest myRequest;
    WebView webview;

    /**
    * This is a very simple example
    * you can pass token and url
    * dynamically like from your Rest API.
    **/
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyYWMiOiI1ZTY2NWMtY2FiZWI4LTJmMDU5Yi0zNTk0YWQiLCJpYXQiOjE1ODM3NjY3MzJ9.e6z8KgU8KP39GDFhzOmPiNMfYUlXx7sgzelJ1lWRYVw"; // Replace with your token integration!
    String url = "https://vvct0.csb.app/?token=" + token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWebView();
    }

    public static boolean hasPermissions(Context context, String[] permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) return false;
            }
        }
        return true;
    }

    private void initWebView() {
        webview = findViewById(R.id.videocall);
        setUpWebViewDefaults(webview);
        webview.loadUrl(url);

        webview.setWebChromeClient(new WebChromeClient() {

            // You can delete this
            public boolean onConsoleMessage(ConsoleMessage m) {
                Log.d(TAG, m.message() + " -- From line " + m.lineNumber() + " of " + m.sourceId());
                return true;
            }

            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                myRequest = request;
                if (!hasPermissions(MainActivity.this, PERMISSIONS)) ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, PERMISSION_ALL);
                else myRequest.grant(myRequest.getResources());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String ListPermissions[], int[] grantResults) {
        /**
        * Here you can know if the camera or microphone was denied with requestCode
        * Ex:
        * switch (requestCode) ...
        */
        if (grantResults.length > 1
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) myRequest.grant(myRequest.getResources());
        else myRequest.deny();
    }

    /**
     * Convenience method to set some generic defaults for a
     * given WebView
     *
     * @param webView
     */
    private void setUpWebViewDefaults(WebView webView) {
        WebSettings settings = webView.getSettings();

        // Enable Javascript
        settings.setJavaScriptEnabled(true);

        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // Enable pinch to zoom without the zoom buttons
        settings.setBuiltInZoomControls(true);

        // Allow use of Local Storage
        settings.setDomStorageEnabled(true);

        // Disable gesture autoplay policy
        settings.setMediaPlaybackRequiresUserGesture(false);

        // Avoid cache for updates
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // Other settings
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            // Hide the zoom controls for HONEYCOMB+
            settings.setDisplayZoomControls(false);
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

        // Enable remote debugging via chrome://inspect
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) WebView.setWebContentsDebuggingEnabled(true);

        // AppRTC requires third party cookies to work
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptThirdPartyCookies(webview, true);

        webView.clearCache(true);
        webView.clearHistory();

        webView.setWebViewClient(new WebViewClient());
    }


}