package com.glsebastiany.heroessample.core

import com.glsebastiany.heroessample.core.application.App
import com.glsebastiany.heroessample.core.di.DaggerApplicationComponent

class TestApp : App() {

    override fun onCreate() {
        super.onCreate()

        startDependencyInjection()
    }


    private fun startDependencyInjection() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(TestApplicationModule(applicationContext.applicationContext))
                .build()
    }
}