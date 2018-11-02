package com.glsebastiany.heroessample.ui.heroes

import com.glsebastiany.bindingrecyclerview.BindingRecyclerView
import com.glsebastiany.heroessample.R
import com.glsebastiany.heroessample.repository.model.CharactersResponse

data class HeroesListItemViewModel(
        val characterId: Int,
        val name: String,
        val description: String,
        val sourceModel: CharactersResponse.Data.CharacterResponse,
        val clickCallback: (CharactersResponse.Data.CharacterResponse) -> Unit
) : BindingRecyclerView.BindableViewHolder {

    override val layoutId: Int = R.layout.layout_item_hero

}