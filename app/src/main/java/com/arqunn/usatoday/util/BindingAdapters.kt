package com.arqunn.usatoday.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.arqunn.usatoday.util.base.adapter.BaseListAdapter
import com.arqunn.usatoday.util.base.adapter.ListAdapterItem
import com.arqunn.usatoday.util.extensions.loadImage

@BindingAdapter("app:adapter")
fun bindRecyclerAdapter(recyclerView: RecyclerView, adapter: BaseListAdapter<ViewDataBinding, ListAdapterItem>?) {
    adapter?.let { recyclerAdapter ->
        recyclerView.adapter = recyclerAdapter
    }
}

@BindingAdapter("app:loadImage")
fun loadImage(imageView: AppCompatImageView, imageUrl: String?) {
    imageView.loadImage(imageUrl)
}