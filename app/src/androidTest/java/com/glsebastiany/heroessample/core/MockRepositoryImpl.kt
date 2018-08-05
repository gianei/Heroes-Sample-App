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
        return Single.just(CharactersResponse(
                "status",
                CharactersResponse.Data(
                        offset,
                        limit,
                        1,
                        1,
                        listOf(
                                CharactersResponse.Data.CharacterResponse(
                                        3,
                                        "Hero 3",
                                        "Description hero 3"
                                )
                        )
                )
        ))
    }

    override fun getHeroComicsPaginated(characterId: Int, offset: Int, limit: Int): Single<CharacterComicsResponse> {
        return Single.just(CharacterComicsResponse(
                "status",
                CharacterComicsResponse.Data(
                        offset,
                        limit,
                        1,
                        1,
                        listOf(
                                CharacterComicsResponse.Data.CharacterComicResponse(
                                        characterId,
                                        "Comic title hero 3",
                                        "Description comic hero 3"
                                )
                        )
                )
        ))
    }
}