package com.istudio.currency_converter.domain.usecases.useCaseTypes

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import com.istudio.core.platform.actions.FlowAction
import com.istudio.core.platform.extensions.connectivityManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@Suppress("DEPRECATION")
class CheckConnectivityUseCase @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : FlowAction<Unit, Boolean>() {

    override suspend fun createFlow(input: Unit): Flow<Boolean> {
        return flowOf(connectivityCheckBasedOnOs())
    }

    private fun connectivityCheckBasedOnOs() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (connectivityManager.activeNetwork != null) {
                val network = connectivityManager.activeNetwork
                checkNetworkCases(network)
            } else {
                false
            }
        } else {
            if (connectivityManager.activeNetworkInfo != null) {
                val networkInfo = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else {
                false
            }
        }

    private fun checkNetworkCases(network: Network?): Boolean {
        var conn : Boolean = false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network)
        if (activeNetwork != null) {
            conn = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        }
        return conn
    }

}