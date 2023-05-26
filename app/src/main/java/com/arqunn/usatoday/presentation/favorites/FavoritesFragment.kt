package com.arqunn.usatoday.presentation.favorites

import androidx.navigation.fragment.findNavController
import com.arqunn.usatoday.R
import com.arqunn.usatoday.databinding.FragmentFavoritesBinding
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.presentation.news.adapter.ArticleAdapter
import com.arqunn.usatoday.util.base.BaseFragment
import com.arqunn.usatoday.util.extensions.collectFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>() {

    private val adapter by lazy {
        ArticleAdapter(::onClickArticle, ::onFavoriteChange)
    }

    override fun getLayoutResource() = R.layout.fragment_favorites

    override fun targetViewModel() = FavoritesViewModel::class.java

    override fun onViewReady() {
        observe()
        binding.adapter = adapter
    }

    private fun observe() {
        viewLifecycleOwner.collectFlow(viewModel.viewState) {
            setViewState(it)
        }
    }

    private fun setViewState(viewState: FavoritesViewState) {
        adapter.submitList(viewState.favorites)
    }

    private fun onClickArticle(article: Article) {
        findNavController().navigate(FavoritesFragmentDirections.actionNavigateNewsDetail(article))
    }

    private fun onFavoriteChange(article: Article, isMyFavorite: Boolean) {
        viewModel.addRemoveFavorite(article, isMyFavorite)
    }
}