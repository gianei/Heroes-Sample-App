package com.glsebastiany.heroessample.ui.core.recyclerview

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.glsebastiany.heroessample.ui.core.base.BaseViewModel

object RVUtil {
    fun setupScrollToLoadMore(recyclerView: RecyclerView, baseViewModel: BaseViewModel, performPaginateCallback: () -> Unit) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //checks for scroll down
                if (dy > 0) {
                    recyclerView.post {
                        // delay handling so that it is executed outside the scroll callback
                        handleScrollDown(recyclerView, baseViewModel, performPaginateCallback)
                    }
                }
            }
        })
    }

    private fun handleScrollDown(recyclerView: RecyclerView, baseViewModel: BaseViewModel, performPaginateCallback: () -> Unit) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

        if (!baseViewModel.isLoading) {
            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                performPaginateCallback()
            }
        }
    }
}