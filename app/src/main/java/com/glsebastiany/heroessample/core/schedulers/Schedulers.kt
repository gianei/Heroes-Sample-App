@file:Suppress("unused")

package com.glsebastiany.heroessample.core.schedulers

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IoToMainScheduler<T> : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())

class IoToIoScheduler<T> : BaseScheduler<T>(Schedulers.io(), Schedulers.io())

class SingleToSingleScheduler<T> : BaseScheduler<T>(Schedulers.single(), Schedulers.single())

class SingleToMainScheduler<T> : BaseScheduler<T>(Schedulers.single(), AndroidSchedulers.mainThread())

class NewToMainScheduler<T> : BaseScheduler<T>(Schedulers.newThread(), AndroidSchedulers.mainThread())

class ComputationToMainScheduler<T> : BaseScheduler<T>(Schedulers.computation(), AndroidSchedulers.mainThread())
