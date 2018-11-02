package com.glsebastiany.heroessample.ui.core.rxsteps

import com.glsebastiany.heroessample.ui.core.base.LoadingStateHolder
import io.reactivex.*

/**
 * Use this step to have an updated loading state
 */
class StepLoadingState<T> constructor(private val loadingStateHolder: LoadingStateHolder) :
        SingleTransformer<T, T>,
        CompletableTransformer {

    override fun apply(upstream: Completable): CompletableSource {
        return upstream
                .doOnSubscribe {
                    loadingStateHolder.onLoadingStateChanged(true)
                }
                .doOnComplete {
                    loadingStateHolder.onLoadingStateChanged(false)
                }
                .doOnError {
                    loadingStateHolder.onLoadingStateChanged(false)
                }
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
                .doOnSubscribe {
                    loadingStateHolder.onLoadingStateChanged(true)
                }
                .doOnSuccess {
                    loadingStateHolder.onLoadingStateChanged(false)
                }
                .doOnError {
                    loadingStateHolder.onLoadingStateChanged(false)
                }
    }

}
