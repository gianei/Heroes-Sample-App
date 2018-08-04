package com.glsebastiany.heroessample.ui.heroes

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.glsebastiany.heroessample.R
import com.glsebastiany.heroessample.databinding.ActivityHeroesBinding
import com.glsebastiany.heroessample.ui.core.base.BaseActivity


class HeroesActivity : BaseActivity<HeroesViewModel, ActivityHeroesBinding>() {

    override val layoutResId = R.layout.activity_heroes

    override val viewModelClass = HeroesViewModel::class.java

    override val autoLoad: Boolean = true

    override fun onAfterCreateView(savedInstanceState: Bundle?) {
        super.onAfterCreateView(savedInstanceState)

        setupScrollToLoadMore()
    }

    private fun setupScrollToLoadMore() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //checks for scroll down
                if (dy > 0) {
                    recyclerView.post {
                        // delay handling so that it is executed outside the scroll callback
                        handleScrollDown(recyclerView)
                    }
                }
            }
        })
    }

    private fun handleScrollDown(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

        if (!viewModel.isLoading) {
            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                viewModel.applyPagination()
            }
        }
    }

    companion object {
        fun launch(context: Context) {
            val intent = android.content.Intent(context, HeroesActivity::class.java)
            intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP or android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}
