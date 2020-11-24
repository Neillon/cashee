package com.neillon.cashee.common.exceptions

import android.annotation.SuppressLint
import android.util.Log

@SuppressLint("LongLogTag")
class NotRegisteredException: Exception("Usuário não cadastrado") {

    init {
        Log.e(TAG, message!!)
    }

    companion object {
        const val TAG = "NotRegisteredException"
    }
}