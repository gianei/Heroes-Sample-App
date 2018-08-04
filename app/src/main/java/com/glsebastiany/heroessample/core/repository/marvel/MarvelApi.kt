package com.glsebastiany.heroessample.core.repository.marvel

import com.glsebastiany.heroessample.ui.heroes.HeroesListItemViewModel
import io.reactivex.Single
import retrofit2.http.GET

interface MarvelApi {

    @GET("characters")
    fun getAllHeroes(): Single<List<HeroesListItemViewModel>>
}