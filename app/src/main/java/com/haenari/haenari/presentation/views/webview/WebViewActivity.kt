package com.haenari.haenari.presentation.views.webview

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.haenari.haenari.R
import com.haenari.haenari.databinding.ActivityWebviewBinding
import com.haenari.haenari.presentation.base.activity.BindActivity
import com.haenari.haenari.presentation.base.custom.CustomWebView

class WebViewActivity : BindActivity<ActivityWebviewBinding>(R.layout.activity_webview) {

    companion object {
        const val EXTRA_LOAD_URL: String = "EXTRA_LOAD_URL"
        const val EXTRA_TITLE: String = "EXTRA_TITLE"
    }

    private var url: String? = null
    private var title: String? = null

    override fun initView() {
        bind {
            btnBack.setOnClickListener {
                finish()
            }

            webView.loadListener = object : CustomWebView.LoadListener {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    showProgressBar()
                }

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?) {
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ) {
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    hideProgressBar()
                }
            }

            if (!url.isNullOrEmpty()) {
                bind {
                    webView.loadUrl(url!!)
                }
            }

            tvTitle.text = title
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        url = intent?.getStringExtra(EXTRA_LOAD_URL) ?: ""
    }

    override fun initData(bundle: Bundle?) {
        url = intent?.getStringExtra(EXTRA_LOAD_URL) ?: ""
        title = intent?.getStringExtra(EXTRA_TITLE) ?: ""
    }

    private fun showProgressBar() {
        bind {
            pbLoading.visibility = View.VISIBLE
        }
    }

    private fun hideProgressBar() {
        bind {
            pbLoading.visibility = View.GONE
        }
    }
}