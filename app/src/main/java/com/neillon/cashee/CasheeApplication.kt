package com.neillon.cashee

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CasheeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CasheeApplication)
            modules(emptyList())
        }

    }
}