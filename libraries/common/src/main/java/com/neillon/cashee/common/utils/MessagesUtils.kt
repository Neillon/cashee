package com.neillon.cashee.common.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

infix fun View.makeSimpleSnackBarWithMessage(message: String) {
    Snackbar.make(
        this,
        message,
        Snackbar.LENGTH_LONG
    ).show()
}