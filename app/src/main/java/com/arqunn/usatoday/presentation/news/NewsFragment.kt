package com.arqunn.usatoday.presentation.news

import com.arqunn.usatoday.R
import com.arqunn.usatoday.databinding.FragmentNewsBinding
import com.arqunn.usatoday.util.base.BaseFragment

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {

    override fun getLayoutResource() = R.layout.fragment_news

    override fun targetViewModel() = NewsViewModel::class.java

    override fun onViewReady() {

    }

}