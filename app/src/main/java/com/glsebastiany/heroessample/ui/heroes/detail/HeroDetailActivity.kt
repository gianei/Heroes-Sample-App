package com.glsebastiany.heroessample.ui.heroes.detail

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.glsebastiany.heroessample.R
import com.glsebastiany.heroessample.core.repository.model.CharactersResponse
import com.glsebastiany.heroessample.databinding.ActivityHeroDetailBinding
import com.glsebastiany.heroessample.ui.core.base.BaseActivity
import com.glsebastiany.heroessample.ui.core.recyclerview.RVUtil


class HeroDetailActivity : BaseActivity<HeroDetailViewModel, ActivityHeroDetailBinding>() {

    override val layoutResId = R.layout.activity_hero_detail

    override val viewModelClass = HeroDetailViewModel::class.java

    override val autoLoad: Boolean = true

    override fun onAfterCreateView(savedInstanceState: Bundle?) {
        super.onAfterCreateView(savedInstanceState)

        setupToolbarBack()

        viewModel.initArguments(intent)

        RVUtil.setupScrollToLoadMore(
                binding.recyclerView,
                viewModel
        ) { viewModel.applyPagination() }
    }

    private fun setupToolbarBack() {
        binding.toolbar.navigationIcon = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        fun launch(context: Context, character: CharactersResponse.Data.CharacterResponse) {
            val intent = android.content.Intent(context, HeroDetailActivity::class.java)
            intent.putExtra(HeroDetailViewModel.ARGUMENT_CHARACTER, character)
            context.startActivity(intent)
        }
    }
}
