package com.glsebastiany.heroessample.ui.core.base

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glsebastiany.heroessample.BR

abstract class BaseFragment<T : BaseViewModel, V : ViewDataBinding> : Fragment() {

    protected lateinit var viewModel: T
    protected abstract val viewModelClass: Class<T>

    protected abstract val layoutResId: Int
    protected lateinit var binding: V

    protected open val autoLoad = false

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel()

        setupErrorListener()
    }

    open fun provideViewModel() = ViewModelProviders.of(this).get(viewModelClass)

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)

        binding.setVariable(BR.viewModel, viewModel)

        SwipeLoadManager.setupSwipeLoaderIfAvailable(viewModel, binding)

        onAfterCreateView(savedInstanceState)

        autoLoad()

        return binding.root
    }

    private fun autoLoad() {
        (viewModel as? LoadableViewModel)?.let {
            if (autoLoad) it.load()
        }
    }

    private fun setupErrorListener() {
        viewModel.showErrorScreen.observe(this, ApiErrorObserver(requireContext()))
    }

    open fun onAfterCreateView(savedInstanceState: Bundle?) {}
}