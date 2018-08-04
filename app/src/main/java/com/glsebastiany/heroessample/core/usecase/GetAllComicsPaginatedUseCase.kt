package com.glsebastiany.heroessample.core.usecase

import com.glsebastiany.heroessample.core.repository.ApiRepository
import com.glsebastiany.heroessample.core.repository.model.CharacterComicsResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetAllComicsPaginatedUseCase @Inject constructor(private val apiRepository: ApiRepository) : BasePaginatedUseCaseSingle<Collection<CharacterComicsResponse.Data.CharacterComicResponse>, GetAllComicsPaginatedUseCase.Params>(Schedulers.io()) {

    data class Params(
            val characterId: Int
    )

    override fun buildUseCaseObservable(params: Params): Single<Collection<CharacterComicsResponse.Data.CharacterComicResponse>> {

        return apiRepository
                .getHeroComicsPaginated(
                        params.characterId,
                        offset,
                        limit
                )
                .doOnSuccess {
                    offset += limit
                    if (offset >= it.data.total) {
                        hasMorePages = false
                    }
                }
                .map { comicsResponse ->
                    comicsResponse.data.results
                }
    }

}