package com.haenari.haenari.presentation.views.settings

import android.content.Intent
import com.haenari.haenari.BuildConfig
import com.haenari.haenari.R
import com.haenari.haenari.databinding.FragmentSettingsBinding
import com.haenari.haenari.presentation.base.fragment.BindFragment
import com.haenari.haenari.presentation.views.webview.WebViewActivity

class SettingsFragment : BindFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {

    companion object {
        fun newInstance() = SettingsFragment()
    }
    override fun initView() {
        bind {
            tvVersion.text = getString(R.string.version_info, BuildConfig.VERSION_NAME)

            tvPrivacyPolicy.setOnClickListener {
                context?.let {
                    val intent = Intent(it, WebViewActivity::class.java).apply {
                        putExtra(WebViewActivity.EXTRA_LOAD_URL, BuildConfig.URL_PRIVACY_POLICY)
                        putExtra(WebViewActivity.EXTRA_TITLE, getString(R.string.privacy_policy))
                    }
                    startActivity(intent)
                }
            }

            tvTermsOfService.setOnClickListener {
                context?.let {
                    val intent = Intent(it, WebViewActivity::class.java).apply {
                        putExtra(WebViewActivity.EXTRA_LOAD_URL, BuildConfig.URL_TERMS_AND_CONDITIONS)
                        putExtra(WebViewActivity.EXTRA_TITLE, getString(R.string.terms_of_service))
                    }
                    startActivity(intent)
                }
            }
        }
    }
}