package com.glsebastiany.heroessample.core.usecase

import io.reactivex.Scheduler
import io.reactivex.Single


abstract class BaseUseCaseSingle<ResultModel, Params>
constructor(private val threadExecutor: Scheduler) {

    abstract fun buildUseCaseObservable(params: Params): Single<ResultModel>

    open fun setup(params: Params) {}

    open fun execute(params: Params): Single<ResultModel> {
        setup(params)
        return this.buildUseCaseObservable(params)
                .subscribeOn(threadExecutor)
    }

}