package com.glsebastiany.heroessample.core.repository

import com.glsebastiany.heroessample.ui.heroes.HeroesListItemViewModel
import io.reactivex.Single

interface ApiRepository {

    fun getHeroes(): Single<out Collection<HeroesListItemViewModel>>

}