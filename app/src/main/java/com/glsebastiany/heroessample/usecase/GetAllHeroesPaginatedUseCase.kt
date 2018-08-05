package com.glsebastiany.heroessample.usecase

import com.glsebastiany.heroessample.repository.ApiRepository
import com.glsebastiany.heroessample.repository.model.CharactersResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GetAllHeroesPaginatedUseCase @Inject constructor(private val apiRepository: ApiRepository) : BasePaginatedUseCaseSingle<Collection<CharactersResponse.Data.CharacterResponse>, Any>(Schedulers.io()) {

    override fun buildUseCaseObservable(params: Any): Single<Collection<CharactersResponse.Data.CharacterResponse>> {

        return apiRepository
                .getHeroesPaginated(
                        offset,
                        limit
                )
                .doOnSuccess {
                    offset += limit
                    if (offset >= it.data.total) {
                        hasMorePages = false
                    }
                }
                .map { charactersResponse ->
                    charactersResponse.data.results
                }
    }

}