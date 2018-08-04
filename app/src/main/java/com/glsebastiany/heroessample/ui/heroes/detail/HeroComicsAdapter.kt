package com.glsebastiany.heroessample.ui.heroes.detail

import com.glsebastiany.heroessample.R
import com.glsebastiany.heroessample.ui.core.recyclerview.RVBaseAdapter

class HeroComicsAdapter : RVBaseAdapter<HeroComicListItemViewModel>() {

    override fun getLayoutIdForPosition(position: Int): Int = R.layout.layout_item_comic

}