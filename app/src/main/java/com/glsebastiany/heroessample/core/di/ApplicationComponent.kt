package com.glsebastiany.heroessample.core.di

import android.content.Context
import com.glsebastiany.heroessample.ui.heroes.HeroesViewModel
import com.glsebastiany.heroessample.ui.heroes.detail.HeroDetailViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ApplicationModule::class)
])
interface ApplicationComponent {

    fun context(): Context

    fun inject(heroesViewModel: HeroesViewModel)

    fun inject(heroDetailViewModel: HeroDetailViewModel)

}
