package com.neillon.cashee.common.exceptions

import android.annotation.SuppressLint
import android.util.Log

@SuppressLint("LongLogTag")
class NoInternetConnectionException(var text: String): Exception(text) {

    init {
        Log.e(TAG, message!!)
    }

    companion object {
        const val TAG = "NoInternetConnectionException"
    }
}