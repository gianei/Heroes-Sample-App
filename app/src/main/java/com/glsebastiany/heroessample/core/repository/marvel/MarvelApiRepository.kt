package com.glsebastiany.heroessample.core.repository.marvel

import com.glsebastiany.heroessample.core.repository.ApiRepository
import com.glsebastiany.heroessample.core.repository.model.CharacterComicsResponse
import com.glsebastiany.heroessample.core.repository.model.CharactersResponse
import io.reactivex.Single
import javax.inject.Inject

class MarvelApiRepository @Inject constructor(private val marvelApi: MarvelApi) : ApiRepository {

    override fun getHeroesPaginated(offset: Int, limit: Int): Single<CharactersResponse> {
        return marvelApi
                .getCharacters(offset, limit)
    }

    override fun getHeroComicsPaginated(characterId: Int, offset: Int, limit: Int): Single<CharacterComicsResponse> {
        return marvelApi
                .getCharacterComics(characterId, offset, limit)
    }
}