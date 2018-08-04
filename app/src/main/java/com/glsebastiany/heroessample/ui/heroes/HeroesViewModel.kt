package com.glsebastiany.heroessample.ui.heroes

import android.app.Application
import com.glsebastiany.heroessample.core.getApplicationComponent
import com.glsebastiany.heroessample.core.repository.ApiRepository
import com.glsebastiany.heroessample.core.schedulers.IoToMainScheduler
import com.glsebastiany.heroessample.ui.core.base.BaseViewModel
import com.glsebastiany.heroessample.ui.core.base.LoadableViewModel
import com.glsebastiany.heroessample.ui.core.base.ViewModelRxTransformer
import io.reactivex.Single
import javax.inject.Inject


class HeroesViewModel(application: Application) : BaseViewModel(application), LoadableViewModel {

    @Inject
    lateinit var apiRepository: ApiRepository

    val heroesAdapter = HeroesAdapter()

    init {
        application.getApplicationComponent().inject(this)
    }

    internal fun getLoadRx(): Single<MutableList<HeroesListItemViewModel>> =
            apiRepository
                    .getHeroes()
                    .map { it.toMutableList() }

    override fun load() {
        getLoadRx()
                .compose(ViewModelRxTransformer(this))
                .compose(IoToMainScheduler())
                .doOnSubscribe { heroesAdapter.clear() }
                .subscribe(onLoadResult) {}
    }

    internal val onLoadResult: (MutableList<HeroesListItemViewModel>) -> Unit = {
        heroesAdapter.viewModels = it
    }

}
