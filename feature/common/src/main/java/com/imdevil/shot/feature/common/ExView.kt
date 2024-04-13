package com.imdevil.shot.feature.common

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
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

fun Fragment.showToast(resId: Int) {
    showToast(getString(resId))
}

fun Fragment.showToast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(resId: Int) {
    showToast(getString(resId))
}

fun Activity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}