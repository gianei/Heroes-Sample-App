package com.glsebastiany.heroessample.ui.core.rxsteps

import io.reactivex.*
import timber.log.Timber


class StepLogErrors<T> :
        SingleTransformer<T, T>,
        CompletableTransformer {

    override fun apply(upstream: Completable): CompletableSource {
        return upstream
                .doOnError {
                    Timber.e(it)
                }
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
                .doOnError {
                    Timber.e(it)
                }
    }

}
