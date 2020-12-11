package com.badmitry.github.ui.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.badmitry.github.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

class AndroidNetworkStatus(context: Context) : INetworkStatus {

    private val statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    init {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(
            request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    statusSubject.onNext(isInternetAvailable())
                }

                override fun onUnavailable() {
                    statusSubject.onNext(false)
                }

                override fun onLost(network: Network) {
                    statusSubject.onNext(false)
                }
            })
    }

    override fun isOnline() = statusSubject

    override fun isOnlineSingle() = statusSubject.first(false)

    private fun isInternetAvailable(): Boolean {
        return try {
            val sock = Socket()
            val socketAddress: SocketAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(socketAddress, 10000)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}