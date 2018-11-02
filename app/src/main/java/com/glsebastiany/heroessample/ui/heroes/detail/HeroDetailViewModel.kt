package com.glsebastiany.heroessample.ui.heroes.detail

import android.app.Application
import android.content.Intent
import com.glsebastiany.heroessample.core.getApplicationComponent
import com.glsebastiany.heroessample.repository.model.CharactersResponse
import com.glsebastiany.heroessample.ui.core.base.BaseViewModel
import com.glsebastiany.heroessample.ui.core.base.LoadableViewModel
import com.glsebastiany.heroessample.ui.core.rxsteps.StepAggregatorViewModel
import com.glsebastiany.heroessample.usecase.GetAllComicsPaginatedUseCase
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class HeroDetailViewModel(application: Application) : BaseViewModel(application), LoadableViewModel {

    @Inject
    lateinit var getAllComicsPaginatedUseCase: GetAllComicsPaginatedUseCase

    val heroComicsAdapter = HeroComicsAdapter()

    var character: CharactersResponse.Data.CharacterResponse? = null

    init {
        application.getApplicationComponent().inject(this)
    }

    internal fun initArguments(arguments: Intent?) {
        character = arguments?.getParcelableExtra(ARGUMENT_CHARACTER)
    }

    internal fun getLoadRx(): Single<MutableList<HeroComicListItemViewModel>> {
        return character?.let { character ->
            getAllComicsPaginatedUseCase
                    .execute(GetAllComicsPaginatedUseCase.Params(character.id))
                    .map { listResult ->
                        listResult
                                .map { result ->
                                    HeroComicListItemViewModel(
                                            result.title
                                    )
                                }
                                .toMutableList()
                    }
        } ?: run {
            Single.error<MutableList<HeroComicListItemViewModel>>(Throwable("Character id is not defined"))
        }
    }

    override fun load() {
        getAllComicsPaginatedUseCase.resetOffset()
        heroComicsAdapter.clear()
        runUseCase()
    }

    private fun runUseCase() {
        getLoadRx()
                .compose(StepAggregatorViewModel(this))
                .subscribeBy(
                        onSuccess = onLoadResult,
                        onError = {}
                )
    }

    fun applyPagination() {
        if (getAllComicsPaginatedUseCase.hasMorePages)
            runUseCase()
    }

    internal val onLoadResult: (MutableList<HeroComicListItemViewModel>) -> Unit = {
        heroComicsAdapter.addViewModels(it)
    }

    companion object {
        internal const val ARGUMENT_CHARACTER = "ARGUMENT_CHARACTER"
    }

}
