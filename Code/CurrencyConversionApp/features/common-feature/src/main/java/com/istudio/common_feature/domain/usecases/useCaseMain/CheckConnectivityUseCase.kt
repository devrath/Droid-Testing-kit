package com.istudio.common_feature.domain.usecases.useCaseMain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.istudio.core.platform.actions.FlowAction
import com.istudio.core.platform.extensions.connectivityManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CheckConnectivityUseCase @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : FlowAction<Unit, Boolean>() {

    override fun createFlow(input: Unit): Flow<Boolean> {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return flowOf(false)
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(network) ?: return flowOf(false)

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> flowOf(true)
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> flowOf(true)
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> flowOf(true)
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> flowOf(true)
                else -> flowOf(false)
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return flowOf(false)
            @Suppress("DEPRECATION")
            return flowOf(networkInfo.isConnected)
        }
    }

}