package com.imdevil.core.common.log

import android.util.Log

object Dog {
    private const val APP_TAG = "[S]"

    fun d(tag: String, msg: String) {
        Log.d("$APP_TAG$tag", msg)
    }

    fun i(tag: String, msg: String) {
        Log.i("$APP_TAG$tag", msg)
    }

    fun e(tag: String, msg: String, tr: Throwable) {
        Log.e("$APP_TAG$tag", msg, tr)
    }
}
