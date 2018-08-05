package com.glsebastiany.heroessample.core

import com.glsebastiany.heroessample.repository.ApiRepository
import com.glsebastiany.heroessample.repository.model.CharacterComicsResponse
import com.glsebastiany.heroessample.repository.model.CharactersResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MockRepositoryImpl @Inject constructor() : ApiRepository {

    override fun getHeroesPaginated(offset: Int, limit: Int): Single<CharactersResponse> {
        TODO("Not implemented yet")
    }

    override fun getHeroComicsPaginated(characterId: Int, offset: Int, limit: Int): Single<CharacterComicsResponse> {
        TODO("Not implemented yet")
    }
}