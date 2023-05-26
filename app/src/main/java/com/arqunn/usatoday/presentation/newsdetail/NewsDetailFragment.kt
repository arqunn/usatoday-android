package com.arqunn.usatoday.presentation.newsdetail

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.arqunn.usatoday.R
import com.arqunn.usatoday.databinding.FragmentNewsDetailBinding
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.util.base.BaseFragment
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding, NewsDetailViewModel>(),
    AppBarLayout.OnOffsetChangedListener {

    private val args: NewsDetailFragmentArgs by navArgs()
    private lateinit var selectedArticle: Article

    override fun getLayoutResource() = R.layout.fragment_news_detail

    override fun targetViewModel() = NewsDetailViewModel::class.java

    override fun onViewReady() {
        readArguments()
        initListeners()
        setupBindings()
    }

    private fun readArguments() {
        selectedArticle = args.article
    }

    private fun initListeners() {
        binding.appbar.addOnOffsetChangedListener(this)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupBindings() = with(binding) {
        this.item = selectedArticle
        this.webView.apply {
            settings.loadsImagesAutomatically = true
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()
            loadUrl(selectedArticle.url)
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val maxScrollRange = appBarLayout.totalScrollRange
        binding.tvToolbarTitle.isVisible = abs(verticalOffset) == maxScrollRange
    }
}