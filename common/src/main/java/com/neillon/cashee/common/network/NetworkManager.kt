package com.neillon.cashee.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.neillon.cashee.common.R
import com.neillon.cashee.common.exceptions.NoInternetConnectionException

class NetworkManager(
    private val context: Context
) {

    suspend fun <T> fetchFromInternet(block: suspend () -> T): T {
        val noInternet = !hasInternet()

        if (noInternet)
            throw NoInternetConnectionException(context.getString(R.string.no_internet_connection))

        return block()
    }

    private fun hasInternet(): Boolean {
        val hasInternet = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val connectivityManager = context.getSystemService(ConnectivityManager::class.java)

            val currentNetwork = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(currentNetwork)

            if (capabilities != null)
                capabilities!!.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            else
                false
        } else {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.activeNetworkInfo != null &&
                    connectivityManager.activeNetworkInfo!!.isConnected
        }

        return hasInternet
    }
}