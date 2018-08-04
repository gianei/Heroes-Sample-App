package com.glsebastiany.heroessample.core.repository.marvel

import com.glsebastiany.heroessample.core.repository.model.CharactersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun getAllHeroes(@Query("offset") offset: Int, @Query("limit") limit: Int): Single<CharactersResponse>
}