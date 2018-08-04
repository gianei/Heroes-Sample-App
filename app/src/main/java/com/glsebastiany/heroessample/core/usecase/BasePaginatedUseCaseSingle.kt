package com.glsebastiany.heroessample.core.usecase

import io.reactivex.Scheduler
import io.reactivex.Single


abstract class BasePaginatedUseCaseSingle<ResultModel, Params>
constructor(private val threadExecutor: Scheduler) {

    internal val limit = 25
    internal var offset = 0

    var hasMorePages = true
        internal set

    fun resetOffset() {
        offset = 0
    }

    abstract fun buildUseCaseObservable(params: Params): Single<ResultModel>

    open fun setup(params: Params) {}

    open fun execute(params: Params): Single<ResultModel> {
        setup(params)
        return this.buildUseCaseObservable(params)
                .subscribeOn(threadExecutor)
    }

}