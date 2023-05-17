package com.arqunn.usatoday.presentation.news.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arqunn.usatoday.R
import com.arqunn.usatoday.databinding.RecyclerviewItemArticleBinding
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.util.base.adapter.BaseListAdapter
import com.arqunn.usatoday.util.base.adapter.BaseViewHolder
import com.arqunn.usatoday.util.extensions.executeAfter

class ArticleAdapter(
    private val onClick: (Article) -> Unit
) : BaseListAdapter<RecyclerviewItemArticleBinding, Article>() {

    override fun getViewType(viewType: Int) = R.layout.recyclerview_item_article

    override fun bind(
        binding: RecyclerviewItemArticleBinding,
        item: Article
    ) {
        binding.executeAfter {
            this.item = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<RecyclerviewItemArticleBinding> {
        return super.onCreateViewHolder(parent, viewType).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                onClick(getItem(position))
            }
        }
    }
}