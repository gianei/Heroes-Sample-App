package com.glsebastiany.heroessample.repository.marvel

import com.glsebastiany.heroessample.repository.ApiRepository
import com.glsebastiany.heroessample.repository.model.CharacterComicsResponse
import com.glsebastiany.heroessample.repository.model.CharactersResponse
import io.reactivex.Single
import javax.inject.Inject

open class MarvelApiRepository @Inject constructor(private val marvelApi: MarvelApi) : ApiRepository {

    override fun getHeroesPaginated(offset: Int, limit: Int): Single<CharactersResponse> {
        return marvelApi
                .getCharacters(offset, limit)
    }

    override fun getHeroComicsPaginated(characterId: Int, offset: Int, limit: Int): Single<CharacterComicsResponse> {
        return marvelApi
                .getCharacterComics(characterId, offset, limit)
    }
}