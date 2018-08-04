package com.glsebastiany.heroessample.core.retrofit.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.glsebastiany.heroessample.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ConnectivityInterceptor @Inject constructor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasInternetConnection()) {
            throw NoConnectivityException(context)
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun hasInternetConnection(): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }

    class NoConnectivityException(context: Context) : IOException(context.getString(R.string.no_network_message))

}