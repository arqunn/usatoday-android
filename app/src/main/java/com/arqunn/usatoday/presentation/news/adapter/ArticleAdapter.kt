package com.arqunn.usatoday.presentation.news.adapter

import com.arqunn.usatoday.R
import com.arqunn.usatoday.databinding.RecyclerviewItemArticleBinding
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.util.base.adapter.BaseListAdapter
import com.arqunn.usatoday.util.extensions.executeAfter

class ArticleAdapter : BaseListAdapter<RecyclerviewItemArticleBinding, Article>() {

    override fun getViewType(viewType: Int) = R.layout.recyclerview_item_article

    override fun bind(
        binding: RecyclerviewItemArticleBinding,
        item: Article
    ) {
        binding.executeAfter {
            this.item = item
        }
    }
}