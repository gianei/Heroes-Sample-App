package com.glsebastiany.heroessample.ui.heroes

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.glsebastiany.heroessample.core.getApplicationComponent
import com.glsebastiany.heroessample.core.repository.model.CharactersResponse
import com.glsebastiany.heroessample.core.schedulers.IoToMainScheduler
import com.glsebastiany.heroessample.core.usecase.GetAllHeroesPaginatedUseCase
import com.glsebastiany.heroessample.ui.core.base.BaseViewModel
import com.glsebastiany.heroessample.ui.core.base.LoadableViewModel
import com.glsebastiany.heroessample.ui.core.base.ViewModelRxTransformer
import io.reactivex.Single
import javax.inject.Inject


class HeroesViewModel(application: Application) : BaseViewModel(application), LoadableViewModel {

    @Inject
    lateinit var getAllHeroesPaginatedUseCase: GetAllHeroesPaginatedUseCase

    val heroesAdapter = HeroesAdapter()

    val detailClickLiveData = MutableLiveData<CharactersResponse.Data.CharacterResponse>()

    init {
        application.getApplicationComponent().inject(this)
    }

    internal fun getLoadRx(): Single<MutableList<HeroesListItemViewModel>> =
            getAllHeroesPaginatedUseCase
                    .execute(Any())
                    .map { listResponse ->
                        listResponse.map { response ->
                            HeroesListItemViewModel(
                                    response.id,
                                    response.name,
                                    response.description,
                                    sourceModel = response,
                                    clickCallback = {
                                        detailClickLiveData.value = it
                                    }
                            )
                        }.toMutableList()
                    }

    override fun load() {
        detailClickLiveData.postValue(null)
        getAllHeroesPaginatedUseCase.resetOffset()
        heroesAdapter.clear()
        runUseCase()
    }

    private fun runUseCase() {
        getLoadRx()
                .compose(ViewModelRxTransformer(this))
                .compose(IoToMainScheduler())
                .subscribe(onLoadResult) {}
    }

    fun applyPagination() {
        if (getAllHeroesPaginatedUseCase.hasMorePages)
            runUseCase()
    }

    internal val onLoadResult: (MutableList<HeroesListItemViewModel>) -> Unit = {
        heroesAdapter.addViewModels(it)
    }

}
