package com.glsebastiany.heroessample.ui.heroes

import com.glsebastiany.heroessample.core.repository.model.CharactersResponse

data class HeroesListItemViewModel(
        val characterId: Int,
        val name: String,
        val sourceModel: CharactersResponse.Data.CharacterResponse,
        val clickCallback: (CharactersResponse.Data.CharacterResponse) -> Unit
)