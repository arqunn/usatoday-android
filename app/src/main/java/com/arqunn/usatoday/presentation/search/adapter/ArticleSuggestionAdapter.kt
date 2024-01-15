package com.arqunn.usatoday.presentation.search.adapter

import android.view.ViewGroup
import com.arqunn.usatoday.R
import com.arqunn.usatoday.databinding.RecyclerviewItemSuggestionArticleBinding
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.util.base.adapter.BaseListAdapter
import com.arqunn.usatoday.util.base.adapter.BaseViewHolder
import com.arqunn.usatoday.util.extensions.executeAfter
import com.arqunn.usatoday.util.extensions.safeOnClick

class ArticleSuggestionAdapter(
    private val onClick: (Article) -> Unit,
) : BaseListAdapter<RecyclerviewItemSuggestionArticleBinding, Article>() {

    override fun getViewType(viewType: Int) = R.layout.recyclerview_item_suggestion_article

    override fun bind(
        binding: RecyclerviewItemSuggestionArticleBinding,
        item: Article
    ) {
        binding.executeAfter {
            this.item = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<RecyclerviewItemSuggestionArticleBinding> {
        return super.onCreateViewHolder(parent, viewType).apply {
            binding.root.setOnClickListener {
                safeOnClick { onClick(getItem(it)) }
            }
        }
    }
}