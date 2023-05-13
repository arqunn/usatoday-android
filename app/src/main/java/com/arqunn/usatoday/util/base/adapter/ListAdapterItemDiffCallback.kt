package com.arqunn.usatoday.util.base.adapter

/**
 * https://github.com/adessoTurkey/android-sample-app/blob/develop/app/src/main/kotlin/com/adesso/movee/base/ListAdapterItemDiffCallback.kt
 * */
import androidx.recyclerview.widget.DiffUtil

class ListAdapterItemDiffCallback<T : ListAdapterItem> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}