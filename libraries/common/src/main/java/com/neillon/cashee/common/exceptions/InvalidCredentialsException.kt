package com.neillon.cashee.common.exceptions

import android.annotation.SuppressLint
import android.util.Log

@SuppressLint("LongLogTag")
class InvalidCredentialsException: Exception("E-mail ou senha inválidos") {

    init {
        Log.e(TAG, message!!)
    }

    companion object {
        const val TAG = "InvalidCredentialsException"
    }
}