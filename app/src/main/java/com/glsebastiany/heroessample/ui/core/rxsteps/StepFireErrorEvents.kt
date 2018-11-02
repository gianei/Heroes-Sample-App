package com.glsebastiany.heroessample.ui.core.rxsteps

import com.glsebastiany.heroessample.core.retrofit.interceptor.ConnectivityInterceptor
import com.glsebastiany.heroessample.ui.core.error.ApiError
import com.glsebastiany.heroessample.ui.core.base.BaseViewModel
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Use this step to fire error events on [BaseViewModel] if an error happens
 */
class StepFireErrorEvents<T> constructor(private val baseViewModel: BaseViewModel) :
        SingleTransformer<T, T>,
        CompletableTransformer {

    override fun apply(upstream: Completable): CompletableSource {
        return upstream
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(this::showErrorScreenInMainThread)
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(this::showErrorScreenInMainThread)
    }

    private fun showErrorScreenInMainThread(throwable: Throwable) {
        baseViewModel.showErrorScreen(
                when (throwable) {
                    is ConnectivityInterceptor.NoConnectivityException -> ApiError.NO_CONNECTIVITY
                    else -> ApiError.UNKNOWN
                }
        )
    }

}
