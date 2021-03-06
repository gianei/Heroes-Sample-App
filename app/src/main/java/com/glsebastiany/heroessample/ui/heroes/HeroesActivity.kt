package com.glsebastiany.heroessample.ui.heroes

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import com.glsebastiany.heroessample.R
import com.glsebastiany.heroessample.repository.model.CharactersResponse
import com.glsebastiany.heroessample.databinding.ActivityHeroesBinding
import com.glsebastiany.heroessample.ui.core.base.BaseActivity
import com.glsebastiany.heroessample.ui.core.recyclerview.RVUtil
import com.glsebastiany.heroessample.ui.heroes.detail.HeroDetailActivity


class HeroesActivity : BaseActivity<HeroesViewModel, ActivityHeroesBinding>() {

    override val layoutResId = R.layout.activity_heroes

    override val viewModelClass = HeroesViewModel::class.java

    override val autoLoad: Boolean = true

    override fun onAfterCreateView(savedInstanceState: Bundle?) {
        super.onAfterCreateView(savedInstanceState)

        viewModel.detailClickLiveData.observe(this, clickObserver)

        RVUtil.setupScrollToLoadMore(
                binding.recyclerView,
                viewModel
        ) { viewModel.applyPagination() }
    }

    private val clickObserver = Observer<CharactersResponse.Data.CharacterResponse> {
        if (it != null)
            HeroDetailActivity.launch(this, it)
    }

    companion object {
        fun launch(context: Context) {
            val intent = android.content.Intent(context, HeroesActivity::class.java)
            intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP or android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}
