package com.imdevil.shot.common.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.goneIf(gone: Boolean) {
    if (gone) {
        visibility = View.GONE
    }
}

fun View.visibleOrGone(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun ImageView.load(url: String) {
    if (url.isEmpty()) return
    Glide.with(this.context).load(url).into(this)
}