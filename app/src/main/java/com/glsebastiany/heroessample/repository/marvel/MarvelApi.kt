package com.glsebastiany.heroessample.repository.marvel

import com.glsebastiany.heroessample.repository.model.CharacterComicsResponse
import com.glsebastiany.heroessample.repository.model.CharactersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun getCharacters(
            @Query("offset") offset: Int,
            @Query("limit") limit: Int
    ): Single<CharactersResponse>

    @GET("characters/{character}/comics")
    fun getCharacterComics(
            @Path("character") characterId: Int,
            @Query("offset") offset: Int,
            @Query("limit") limit: Int
    ): Single<CharacterComicsResponse>
}