package com.glsebastiany.heroessample.core.repository.mock

import com.glsebastiany.heroessample.core.repository.ApiRepository
import com.glsebastiany.heroessample.ui.heroes.HeroesListItemViewModel
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MockRepositoryImpl @Inject constructor() : ApiRepository {

    override fun getHeroes(): Single<out Collection<HeroesListItemViewModel>> {
        return Single.defer {
            Single.just(
                    listOf(
                            HeroesListItemViewModel("Hulk"),
                            HeroesListItemViewModel("Spider Man")
                    )
            )
        }.delay(400, TimeUnit.MILLISECONDS)
    }
}