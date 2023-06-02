package com.arqunn.usatoday.presentation.search

import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.arqunn.usatoday.R
import com.arqunn.usatoday.databinding.FragmentSearchBinding
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.presentation.search.adapter.ArticleSuggestionAdapter
import com.arqunn.usatoday.util.base.BaseFragment
import com.arqunn.usatoday.util.extensions.collectFlow
import com.erkutaras.statelayout.StateLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    private val adapter by lazy {
        ArticleSuggestionAdapter(::onClickArticleSuggestion)
    }

    override fun getLayoutResource() = R.layout.fragment_search

    override fun targetViewModel() = SearchViewModel::class.java

    override fun onViewReady() {
        initListeners()
        observe()
        binding.adapter = adapter
    }

    private fun initListeners() {
        binding.etSearch.addTextChangedListener {
            viewModel.searchForNews(it.toString())
        }
    }

    private fun observe() {
        viewLifecycleOwner.collectFlow(viewModel.viewState) {
            setViewState(it)
        }
    }

    private fun setViewState(viewState: SearchViewState) {
        when (viewState) {
            is SearchViewState.Error -> {
                binding.stateLayout.showError(
                    StateLayout.StateInfo(
                        infoImage = R.drawable.ic_no_result,
                        infoTitle = "Whoops...",
                        infoMessage = "No result found"
                    )
                )
            }
            is SearchViewState.Success -> {
                adapter.submitList(viewState.data.articles)
                binding.stateLayout.content()
            }
            is SearchViewState.Loading -> {
                binding.stateLayout.loading()
            }
        }
    }

    private fun onClickArticleSuggestion(article: Article) {
        findNavController().navigate(SearchFragmentDirections.actionNavigateNewsDetail(article))
    }
}