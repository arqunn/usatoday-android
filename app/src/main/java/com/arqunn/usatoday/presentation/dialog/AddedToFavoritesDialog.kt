package com.arqunn.usatoday.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.arqunn.usatoday.R

class AddedToFavoritesDialog(context: Context) : Dialog(context, R.style.DialogToastStyle) {

    init {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_added_to_favorites)
        window?.let {
            val layoutParams = it.attributes
            layoutParams.width = MATCH_PARENT
            layoutParams.height = WRAP_CONTENT
            layoutParams.gravity = Gravity.TOP
            it.attributes = layoutParams
        }
    }

    override fun show() {
        super.show()
        Handler(Looper.getMainLooper())
            .postDelayed({ dismiss() }, 2000)
    }
}