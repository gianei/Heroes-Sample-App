package com.glsebastiany.heroessample.core.di

import android.content.Context
import com.glsebastiany.heroessample.ui.heroes.HeroesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ApplicationModule::class)
])
interface ApplicationComponent {

    fun context(): Context

    fun inject(heroesViewModel: HeroesViewModel)

}
