package com.arqunn.usatoday.util.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<VB : ViewDataBinding, T : ListAdapterItem>(
    diffCallback: DiffUtil.ItemCallback<T> = ListAdapterItemDiffCallback()
) : ListAdapter<T, BaseViewHolder<VB>>(diffCallback) {

    @LayoutRes
    protected abstract fun getViewType(viewType: Int): Int

    protected abstract fun bind(binding: VB, item: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getViewType(viewType),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        bind(holder.binding, getItem(position))
    }
}