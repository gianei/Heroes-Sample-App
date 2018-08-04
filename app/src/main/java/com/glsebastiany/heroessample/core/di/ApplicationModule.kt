package com.glsebastiany.heroessample.core.di

import android.content.Context
import com.glsebastiany.heroessample.core.repository.ApiRepository
import com.glsebastiany.heroessample.core.repository.mock.MockRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
class ApplicationModule(context: Context) {
    private val context: Context = context.applicationContext

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = this.context

//    @Provides
//    @Singleton
//    internal fun provideMarvelApi(repository: RetrofitFactory): ApiRepository =
//            MarvelApiRepository(repository.createRetrofitService(MarvelApi::class.java))

}
