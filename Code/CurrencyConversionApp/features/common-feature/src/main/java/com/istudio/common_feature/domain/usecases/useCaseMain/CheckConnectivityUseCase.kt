package com.istudio.common_feature.domain.usecases.useCaseMain

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

    override fun createFlow(input: Unit): Flow<Boolean> {

        var isConnectedToInternet = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(connectivityManager.activeNetwork!=null){
                val network = connectivityManager.activeNetwork
                isConnectedToInternet = checkNetworkCases(network, isConnectedToInternet)
            }else{
                isConnectedToInternet = false
            }
        } else {
            if(connectivityManager.activeNetworkInfo!=null){
                val networkInfo = connectivityManager.activeNetworkInfo
                isConnectedToInternet = networkInfo?.isConnected ?: false
            }else{
                isConnectedToInternet = false
            }
        }
        return flowOf(isConnectedToInternet)
    }

    private fun checkNetworkCases(
        network: Network?,
        isConnectedToInternet: Boolean
    ): Boolean {
        var isConnectedToInternet1 = isConnectedToInternet
        val activeNetwork = connectivityManager.getNetworkCapabilities(network)
        if (activeNetwork != null) {
            when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    isConnectedToInternet1 = true
                }

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    isConnectedToInternet1 = true
                }

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    isConnectedToInternet1 = true
                }

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> {
                    isConnectedToInternet1 = true
                }

                else -> {
                    isConnectedToInternet1 = false
                }
            }
        }
        return isConnectedToInternet1
    }

}