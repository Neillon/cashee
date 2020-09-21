package com.neillon.cashee.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

infix fun <T> AppCompatActivity.goToActivity(activityToGo: Class<T>) {
    Intent(this@goToActivity, activityToGo)
        .apply {
            startActivity(this)
            this@goToActivity.finish()
        }
}