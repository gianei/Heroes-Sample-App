package com.glsebastiany.heroessample.ui.core.base

import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding
import android.support.v4.widget.SwipeRefreshLayout
import com.glsebastiany.heroessample.R

object SwipeLoadManager {

    fun setupSwipeLoaderIfAvailable(viewModel: ViewModel, binding: ViewDataBinding) {

        (viewModel as? LoadableViewModel)?.let { loadableViewModel ->
            val swipeRefreshLayout: SwipeRefreshLayout? = binding.root.findViewById(R.id.swipeRefreshLayout)

            swipeRefreshLayout?.setOnRefreshListener {
                loadableViewModel.load()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }
}