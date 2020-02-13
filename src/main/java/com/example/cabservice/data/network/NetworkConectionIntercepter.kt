package com.example.cabservice.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response
import android.net.NetworkInfo
import com.example.cabservice.util.NoInternetException


class NetworkConectionIntercepter(context: Context) : Interceptor {

    private val applicationContext=context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("Make sure you have an active data service")

        return chain.proceed(chain.request())
    }

    @Suppress("DEPRECATION")
    private fun isInternetAvailable():Boolean{
        val connecivtyManager=applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connecivtyManager.activeNetwork
            if (network != null) {
                val nc = connecivtyManager.getNetworkCapabilities(network)

                return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
            }
        }else{
            val networkInfos = connecivtyManager.getAllNetworkInfo()
            for (tempNetworkInfo in networkInfos) {
                if (tempNetworkInfo.isConnected) {
                    return true
                }
            }
        }
       return false
    }
}