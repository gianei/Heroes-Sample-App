package com.glsebastiany.heroessample.ui.core.base

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import com.glsebastiany.heroessample.BR
import com.glsebastiany.heroessample.ui.core.error.ApiErrorObserver

abstract class BaseActivity<T : BaseViewModel, V : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: T
    protected abstract val viewModelClass: Class<T>

    protected abstract val layoutResId: Int
    protected lateinit var binding: V

    protected open val autoLoad = false

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResId)

        viewModel = provideViewModel()
        binding.setVariable(BR.viewModel, viewModel)

        SwipeLoadManager.setupSwipeLoaderIfAvailable(viewModel, binding)

        onAfterCreateView(savedInstanceState)

        autoLoad()

        setupErrorListener()
    }

    open fun provideViewModel() = ViewModelProviders.of(this).get(viewModelClass)

    private fun autoLoad() {
        (viewModel as? LoadableViewModel)?.let {
            if (autoLoad) it.load()
        }
    }

    private fun setupErrorListener() {
        viewModel.showErrorScreen.observe(this, ApiErrorObserver(this))
    }

    open fun onAfterCreateView(savedInstanceState: Bundle?) {}

}