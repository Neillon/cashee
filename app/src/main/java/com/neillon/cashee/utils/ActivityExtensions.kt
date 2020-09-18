package com.neillon.cashee.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun <T> AppCompatActivity.callActivity(screen: Class<T>) {
    Intent(this, screen)
        .apply {
            startActivity(this)
            this@callActivity.finish()
        }
}