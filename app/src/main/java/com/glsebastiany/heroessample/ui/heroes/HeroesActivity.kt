package com.glsebastiany.heroessample.ui.heroes

import android.content.Context
import com.glsebastiany.heroessample.R
import com.glsebastiany.heroessample.databinding.ActivityHeroesBinding
import com.glsebastiany.heroessample.ui.core.base.BaseActivity


class HeroesActivity : BaseActivity<HeroesViewModel, ActivityHeroesBinding>() {

    override val layoutResId = R.layout.activity_heroes

    override val viewModelClass = HeroesViewModel::class.java

    override val autoLoad: Boolean = true

    companion object {
        fun launch(context: Context) {
            val intent = android.content.Intent(context, HeroesActivity::class.java)
            intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP or android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}
