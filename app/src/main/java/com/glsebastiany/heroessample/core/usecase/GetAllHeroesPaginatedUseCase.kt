package com.glsebastiany.heroessample.core.usecase

import com.glsebastiany.heroessample.core.repository.ApiRepository
import com.glsebastiany.heroessample.core.repository.model.CharactersResponse
import com.glsebastiany.heroessample.ui.heroes.HeroesListItemViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GetAllHeroesPaginatedUseCase @Inject constructor(private val apiRepository: ApiRepository) : BaseUseCaseSingle<Collection<CharactersResponse.Data.CharacterResponse>, Any>(Schedulers.io()) {

    private val limit = 25
    private var offset = 0

    var hasMorePages = true
        private set

    fun resetOffset() {
        offset = 0
    }

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