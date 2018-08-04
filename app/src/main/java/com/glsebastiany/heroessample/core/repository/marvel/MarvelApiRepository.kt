package com.glsebastiany.heroessample.core.repository.marvel

import com.glsebastiany.heroessample.core.repository.ApiRepository
import com.glsebastiany.heroessample.ui.heroes.HeroesListItemViewModel
import io.reactivex.Single
import javax.inject.Inject

class MarvelApiRepository @Inject constructor(private val marvelApi: MarvelApi) : ApiRepository {

    override fun getHeroes(): Single<out Collection<HeroesListItemViewModel>> {
        return marvelApi.getAllHeroes()
    }
}