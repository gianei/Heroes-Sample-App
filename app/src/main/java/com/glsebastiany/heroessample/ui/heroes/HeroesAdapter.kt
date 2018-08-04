package com.glsebastiany.heroessample.ui.heroes

import com.glsebastiany.heroessample.R
import com.glsebastiany.heroessample.ui.core.recyclerview.RVBaseAdapter

class HeroesAdapter : RVBaseAdapter<HeroesListItemViewModel>() {

    override fun getLayoutIdForPosition(position: Int): Int = R.layout.layout_item_hero

}