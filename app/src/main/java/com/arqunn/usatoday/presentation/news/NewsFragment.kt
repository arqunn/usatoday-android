package com.arqunn.usatoday.presentation.news

import com.arqunn.usatoday.R
import com.arqunn.usatoday.databinding.FragmentNewsBinding
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.presentation.news.adapter.ArticleAdapter
import com.arqunn.usatoday.util.base.BaseFragment
import com.arqunn.usatoday.util.extensions.collectFlow
import com.arqunn.usatoday.util.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {

    private val adapter by lazy {
        ArticleAdapter(::onClickArticle)
    }

    override fun getLayoutResource() = R.layout.fragment_news

    override fun targetViewModel() = NewsViewModel::class.java

    override fun onViewReady() {
        observe()
        binding.adapter = adapter
    }

    private fun observe() {
        viewLifecycleOwner.collectFlow(viewModel.viewState) {
            setViewState(it)
        }
    }

    private fun setViewState(viewState: NewsViewState) {
        when (viewState) {
            is NewsViewState.Error -> {

            }
            is NewsViewState.Success -> {
                adapter.submitList(viewState.data.articles)
            }
            is NewsViewState.Loading -> {

            }
        }
    }

    private fun onClickArticle(article: Article) {
        context?.showToast("redirect to detail page")
    }

}