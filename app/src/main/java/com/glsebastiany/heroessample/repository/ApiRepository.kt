package com.glsebastiany.heroessample.repository

import com.glsebastiany.heroessample.repository.model.CharacterComicsResponse
import com.glsebastiany.heroessample.repository.model.CharactersResponse
import io.reactivex.Single

interface ApiRepository {

    fun getHeroesPaginated(offset: Int, limit: Int): Single<CharactersResponse>
    fun getHeroComicsPaginated(characterId: Int, offset: Int, limit: Int): Single<CharacterComicsResponse>

}