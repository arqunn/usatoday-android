package com.arqunn.usatoday.presentation.newsdetail

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.arqunn.usatoday.R
import com.arqunn.usatoday.databinding.FragmentNewsDetailBinding
import com.arqunn.usatoday.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding, NewsDetailViewModel>() {

    private val args: NewsDetailFragmentArgs by navArgs()

    override fun getLayoutResource() = R.layout.fragment_news_detail

    override fun targetViewModel() = NewsDetailViewModel::class.java

    override fun onViewReady() {
        val article = args.article
        binding.item = article

        setupWebView(article.url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(webUrl: String) = with(binding) {
        webView.apply {
            settings.loadsImagesAutomatically = true
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()
            loadUrl(webUrl)
        }
    }
}