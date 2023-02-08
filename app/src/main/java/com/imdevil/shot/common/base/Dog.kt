package com.imdevil.shot.common.base

import android.util.Log

private const val LOG_PREFIX = "sh0t-"

class Dog {

    companion object {
        fun d(tag: String, msg: String) {
            Log.d("$LOG_PREFIX$tag", msg)
        }
    }
}