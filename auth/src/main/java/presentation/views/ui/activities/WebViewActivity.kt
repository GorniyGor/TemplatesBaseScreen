package presentation.views.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import presentation.views.ui.activities.RegistrationActivity.Companion.URL_EXTRA

/**
 * Created by Gor on 11.04.2018.
 */
class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webView = WebView(this)
        setContentView(webView)
        supportActionBar?.title = ""
        webView.settings.javaScriptEnabled = true
        if (savedInstanceState == null) {
            loadingWebView(intent.getStringExtra(URL_EXTRA))
        }
    }

    private fun loadingWebView(s: String) {
        webView.loadUrl(s)
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
    }
}