package com.arqunn.usatoday.presentation.newsdetail

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.arqunn.usatoday.R
import com.arqunn.usatoday.databinding.FragmentNewsDetailBinding
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.presentation.MainActivity
import com.arqunn.usatoday.util.base.BaseFragment
import com.arqunn.usatoday.util.extensions.collectFlow
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding, NewsDetailViewModel>(),
    AppBarLayout.OnOffsetChangedListener, OnCheckedChangeListener {

    private val args: NewsDetailFragmentArgs by navArgs()
    private lateinit var selectedArticle: Article

    override fun getLayoutResource() = R.layout.fragment_news_detail

    override fun targetViewModel() = NewsDetailViewModel::class.java

    override fun onViewReady() {
        readArguments()
        initListeners()
        setupBindings()
        observe()
        viewModel.checkIfFavorite(selectedArticle)
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

    private fun observe() {
        viewLifecycleOwner.collectFlow(viewModel.eventFlow) {
            handleNewsDetailViewEvents(it)
        }
    }

    private fun handleNewsDetailViewEvents(event: NewsDetailViewEvent) {
        when (event) {
            is NewsDetailViewEvent.ShowAddedToFavoritesDialog -> {
                (requireActivity() as MainActivity).showAddedToFavoritesDialog()
            }
            is NewsDetailViewEvent.ShowFavoriteButton -> {
                binding.cbFavorite.isChecked = event.isMyFavorite
                binding.cbFavorite.setOnCheckedChangeListener(this@NewsDetailFragment)
                binding.cbFavorite.isVisible = true
            }
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val maxScrollRange = appBarLayout.totalScrollRange
        binding.tvToolbarTitle.isVisible = abs(verticalOffset) == maxScrollRange
    }

    override fun onCheckedChanged(checkBox: CompoundButton?, isChecked: Boolean) {
        viewModel.addRemoveFavorite(selectedArticle, isChecked)
    }
}