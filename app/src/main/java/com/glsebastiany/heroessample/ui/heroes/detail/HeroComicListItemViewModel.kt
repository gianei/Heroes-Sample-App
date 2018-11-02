package com.glsebastiany.heroessample.ui.heroes.detail

import com.glsebastiany.bindingrecyclerview.BindingRecyclerView
import com.glsebastiany.heroessample.R

data class HeroComicListItemViewModel(
        val title: String
) : BindingRecyclerView.BindableViewHolder {

    override val layoutId: Int = R.layout.layout_item_comic

}