package com.glsebastiany.heroessample.core

import android.content.Context
import com.glsebastiany.heroessample.core.di.ApplicationModule
import com.glsebastiany.heroessample.core.repository.ApiRepository
import com.glsebastiany.heroessample.core.retrofit.factory.RetrofitFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
class TestApplicationModule(context: Context) : ApplicationModule(context) {

    @Provides
    @Singleton
    override fun provideMarvelApi(repository: RetrofitFactory): ApiRepository =
            MockRepositoryImpl()

}
