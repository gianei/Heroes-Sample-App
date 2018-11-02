package com.glsebastiany.heroessample.ui.core.rxsteps

import io.reactivex.*

/**
 * Use this step to have an updated loading state
 */
class StepLoadingState<T> constructor(private val onLoadingStateChanged: (Boolean) -> Unit) :
        SingleTransformer<T, T>,
        CompletableTransformer {

    override fun apply(upstream: Completable): CompletableSource {
        return upstream
                .doOnSubscribe {
                    onLoadingStateChanged(true)
                }
                .doOnComplete {
                    onLoadingStateChanged(false)
                }
                .doOnError {
                    onLoadingStateChanged(false)
                }
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
                .doOnSubscribe {
                    onLoadingStateChanged(true)
                }
                .doOnSuccess {
                    onLoadingStateChanged(false)
                }
                .doOnError {
                    onLoadingStateChanged(false)
                }
    }

}
