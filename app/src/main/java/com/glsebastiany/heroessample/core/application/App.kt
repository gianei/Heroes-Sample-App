package com.glsebastiany.heroessample.core.application

import android.app.Application
import com.glsebastiany.bindingrecyclerview.BindingRecyclerViewConfig
import com.glsebastiany.heroessample.BR
import com.glsebastiany.heroessample.core.di.ApplicationComponent
import com.glsebastiany.heroessample.core.di.ApplicationModule
import com.glsebastiany.heroessample.core.di.DaggerApplicationComponent

import timber.log.Timber

open class App : Application() {

    lateinit var applicationComponent: ApplicationComponent
        internal set

    override fun onCreate() {
        super.onCreate()

        startDependencyInjection()

        configureTimber()

        configureBindingRecyclerViewAdapter()

    }

    private fun configureTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun startDependencyInjection() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(applicationContext.applicationContext))
                .build()
    }

    private fun configureBindingRecyclerViewAdapter() {
        BindingRecyclerViewConfig.init(object: BindingRecyclerViewConfig.ViewModelBrId() {
            override val id: Int = BR.viewModel
        })
    }

}
