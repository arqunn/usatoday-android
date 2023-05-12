package com.arqunn.usatoday.presentation.news

import com.arqunn.usatoday.R
import com.arqunn.usatoday.databinding.FragmentNewsBinding
import com.arqunn.usatoday.util.base.BaseFragment
import com.arqunn.usatoday.util.extensions.collectFlow

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {

    override fun getLayoutResource() = R.layout.fragment_news

    override fun targetViewModel() = NewsViewModel::class.java

    override fun onViewReady() {
        observe()
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

            }
            is NewsViewState.Loading -> {

            }
        }
    }
}