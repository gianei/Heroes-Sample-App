package com.glsebastiany.heroessample.ui.core.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import android.support.annotation.CallSuper
import com.glsebastiany.heroessample.BR
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(val app: Application) : AndroidViewModel(app), Observable, LoadingStateHolder {

    // region binding setup

    private val registry = PropertyChangeRegistry()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.add(callback)
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    internal fun notifyPropertyChanged(fieldId: Int) {
        registry.notifyChange(this, fieldId)
    }

    //endregion

    //region loading state

    @get:Bindable
    var isLoading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)
        }

    override fun onLoadingStateChanged(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    //endregion

    //region disposable management

    internal val disposables: CompositeDisposable = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }

        super.onCleared()
    }

    //endregion

    val showErrorScreen = MutableLiveData<Boolean>()

    /**
     * Must call this from main thread
     */
    fun showErrorScreen() {
        showErrorScreen.value = true
        showErrorScreen.value = false
    }

}