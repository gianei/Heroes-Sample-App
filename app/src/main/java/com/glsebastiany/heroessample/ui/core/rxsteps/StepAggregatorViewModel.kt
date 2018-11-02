package com.glsebastiany.heroessample.ui.core.rxsteps

import com.glsebastiany.heroessample.core.schedulers.IoToMainScheduler
import com.glsebastiany.heroessample.ui.core.base.BaseViewModel
import io.reactivex.*
import io.reactivex.schedulers.Schedulers

class StepAggregatorViewModel<T> constructor(private val baseViewModel: BaseViewModel) :
        SingleTransformer<T, T>,
        CompletableTransformer {

    override fun apply(upstream: Completable): CompletableSource {
        return upstream
                .observeOn(Schedulers.io())
                .compose(StepLoadingState<T> { isLoading -> baseViewModel.isLoading = isLoading })
                .compose(StepLogErrors<T>())
                .compose(StepFireErrorEvents<T>(baseViewModel))
                .compose(IoToMainScheduler<T>())
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
                .observeOn(Schedulers.io())
                .compose(StepLoadingState<T> { isLoading -> baseViewModel.isLoading = isLoading })
                .compose(StepLogErrors<T>())
                .compose(StepFireErrorEvents<T>(baseViewModel))
                .compose(IoToMainScheduler<T>())
    }

}
