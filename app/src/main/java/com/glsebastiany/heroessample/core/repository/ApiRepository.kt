package com.glsebastiany.heroessample.core.repository

import com.glsebastiany.heroessample.core.repository.model.CharactersResponse
import com.glsebastiany.heroessample.ui.heroes.HeroesListItemViewModel
import io.reactivex.Single

interface ApiRepository {

    fun getHeroesPaginated(offset: Int, limit: Int): Single<CharactersResponse>

}