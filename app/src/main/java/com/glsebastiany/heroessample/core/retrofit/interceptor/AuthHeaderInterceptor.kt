package com.glsebastiany.heroessample.core.retrofit.interceptor

import com.glsebastiany.heroessample.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.security.MessageDigest
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthHeaderInterceptor
@Inject
constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val ts =  Date().time.toString()
        val privateKey = BuildConfig.MarvelPrivateApiKey
        val publicKey = BuildConfig.MarvelPublicApiKey

        val hash = md5(ts + privateKey + publicKey)

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("ts", ts)
                .addQueryParameter("hash", hash)
                .addQueryParameter("apikey", publicKey)
                .build()

        val requestBuilder = original.newBuilder()
                .url(url)

        return chain.proceed(requestBuilder.build())
    }

    private fun md5(string: String): String {
        val md = MessageDigest.getInstance("MD5")
        val digested = md.digest(string.toByteArray())
        return digested.joinToString("") {
            String.format("%02x", it)
        }
    }
}