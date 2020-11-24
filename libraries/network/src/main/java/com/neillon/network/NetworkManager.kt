package com.neillon.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.neillon.cashee.common.R
import com.neillon.cashee.common.exceptions.NoInternetConnectionException

class NetworkManager(
    private val context: Context,
    private val networkStatusManager: INetworkStatusManager
) {

    suspend fun <T> fetchFromInternet(block: suspend () -> T): T {
        val noInternet = !networkStatusManager.hasInternet()

        if (noInternet)
            throw NoInternetConnectionException(context.getString(R.string.no_internet_connection))

        return block()
    }


}