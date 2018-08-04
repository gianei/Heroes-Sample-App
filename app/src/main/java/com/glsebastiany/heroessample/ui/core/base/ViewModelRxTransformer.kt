package com.glsebastiany.heroessample.ui.core.base

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ViewModelRxTransformer<T> constructor(private val baseViewModel: BaseViewModel) :
        SingleTransformer<T, T>,
        CompletableTransformer {

    override fun apply(upstream: Completable): CompletableSource {
        return upstream
                .observeOn(Schedulers.io())
                .doOnSubscribe {
                    registerSubscription(it)
                }
                .doOnComplete {
                    stopLoading()
                }
                .doOnError {
                    logError(it)
                }

                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    showErrorScreenInMainThread()
                }
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
                .observeOn(Schedulers.io())
                .doOnSubscribe {
                    registerSubscription(it)
                }
                .doOnSuccess {
                    stopLoading()
                }
                .doOnError {
                    baseViewModel.isLoading = false
                    Timber.e(it)
                }

                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    showErrorScreenInMainThread()
                }
    }

    private fun logError(it: Throwable?) {
        baseViewModel.isLoading = false
        Timber.e(it)
    }

    private fun stopLoading() {
        baseViewModel.isLoading = false
    }

    private fun registerSubscription(it: Disposable) {
        baseViewModel.isLoading = true
        baseViewModel.registerDisposable(it)
    }

    private fun showErrorScreenInMainThread() {
        baseViewModel.showErrorScreen()
    }
}
