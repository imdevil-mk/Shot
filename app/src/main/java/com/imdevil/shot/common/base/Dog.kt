package com.imdevil.shot.common.base

import android.util.Log

private const val LOG_PREFIX = "sh0t-"

class Dog {

    companion object {
        fun d(tag: String, msg: String) {
            Log.d("$LOG_PREFIX$tag", msg)
        }

        fun e(tag: String, msg: String) {
            Log.e("$LOG_PREFIX$tag", msg)
        }

        fun e(tag: String, msg: String, tr: Throwable) {
            Log.e("$LOG_PREFIX$tag", msg, tr)
        }

        fun w(tag: String, msg: String) {
            Log.w("$LOG_PREFIX$tag", msg)
        }

        fun i(tag: String, msg: String) {
            Log.i("$LOG_PREFIX$tag", msg)
        }
    }
}