package com.glsebastiany.heroessample.core.di

import android.content.Context
import com.glsebastiany.heroessample.core.repository.ApiRepository
import com.glsebastiany.heroessample.core.repository.marvel.MarvelApi
import com.glsebastiany.heroessample.core.repository.marvel.MarvelApiRepository
import com.glsebastiany.heroessample.core.retrofit.factory.RetrofitFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
open class ApplicationModule(context: Context) {
    private val context: Context = context.applicationContext

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = this.context

    @Provides
    @Singleton
    internal open fun provideMarvelApi(repository: RetrofitFactory): ApiRepository =
            MarvelApiRepository(repository.createRetrofitService(MarvelApi::class.java))

}
