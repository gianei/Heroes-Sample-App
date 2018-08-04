package com.glsebastiany.heroessample.core.retrofit.factory

import com.glsebastiany.heroessample.core.retrofit.interceptor.AuthHeaderInterceptor
import com.glsebastiany.heroessample.core.retrofit.interceptor.ConnectivityInterceptor
import com.glsebastiany.heroessample.core.retrofit.interceptor.LanguageHeaderInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofitFactory @Inject constructor(
        connectivityInterceptor: ConnectivityInterceptor,
        languageHeaderInterceptor: LanguageHeaderInterceptor,
        authHeaderInterceptor: AuthHeaderInterceptor) {

    private val rxAdapter: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    val retrofit: Retrofit

    init {

        val gson = GsonBuilder()
                //.setDateFormat(PathDate.DATETIME_FORMAT)
                .create()

        val clientBuilder = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(languageHeaderInterceptor)
                .addInterceptor(authHeaderInterceptor)

        val client = clientBuilder.build()

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build()
    }

    fun <T> createRetrofitService(ggsApiClass: Class<T>): T = retrofit.create(ggsApiClass)

    companion object {
        private const val BASE_API_URL = "https://gateway.marvel.com/v1/public/"
    }
}