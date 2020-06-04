package com.rudhra.newsdoc.ui.newsDetails

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rudhra.newsdoc.R
import com.rudhra.newsdoc.databinding.ActivityNewsDetailsBinding


class NewsDetailsActivity : AppCompatActivity() {
    companion object {
        const val NEWS_ITEM = "NEWS_ITEM"
    }

    private lateinit var binding: ActivityNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(getLink())
        }
    }

    private fun getLink(): String {
        return intent?.getStringExtra(NEWS_ITEM) ?: ""
    }
}
