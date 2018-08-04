package com.glsebastiany.heroessample.core.retrofit.interceptor

import android.content.Context
import com.glsebastiany.heroessample.core.getDefaultLocale
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageHeaderInterceptor
@Inject
constructor(context: Context) : Interceptor {

    private val acceptLanguage: String = context.resources.configuration.getDefaultLocale().language.toLowerCase()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
                .header("Accept-Language", acceptLanguage)

        return chain.proceed(requestBuilder.build())
    }

}