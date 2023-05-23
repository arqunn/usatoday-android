package com.arqunn.usatoday.util.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(this).load(url).into(this)
}