package com.glsebastiany.heroessample.core.application

import android.app.Application
import com.glsebastiany.heroessample.core.di.ApplicationComponent
import com.glsebastiany.heroessample.core.di.ApplicationModule
import com.glsebastiany.heroessample.core.di.DaggerApplicationComponent

import timber.log.Timber

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()

        startDependencyInjection()

        configureTimber()

    }

    private fun configureTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun startDependencyInjection() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(applicationContext.applicationContext))
                .build()
    }
}