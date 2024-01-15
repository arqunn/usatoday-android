package com.arqunn.usatoday.util.extensions

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(this).load(url).into(this)
}

fun RecyclerView.ViewHolder.safeOnClick(action: (position: Int) -> Unit) {
    this.bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }?.let(action)
}