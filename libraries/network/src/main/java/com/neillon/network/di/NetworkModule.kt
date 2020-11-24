package com.neillon.network.di

import com.neillon.network.INetworkStatusManager
import com.neillon.network.NetworkManager
import com.neillon.network.NetworkStatusManager
import org.koin.dsl.module

object NetworkModule {
    val dependencies = module {
        single<INetworkStatusManager> { NetworkStatusManager(context = get()) }
        single { NetworkManager(context = get(), networkStatusManager = get()) }
    }
}