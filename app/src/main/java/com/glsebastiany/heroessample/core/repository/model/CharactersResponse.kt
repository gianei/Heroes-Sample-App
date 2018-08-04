package com.glsebastiany.heroessample.core.repository.model

data class CharactersResponse (
        val status: String,
        val data: Data
) {

    data class Data(
            val offset: Int,
            val limit: Int,
            val total: Int,
            val count: Int,
            val results: List<CharacterResponse>
    ) {
        data class CharacterResponse(
                val id: Int,
                val name: String,
                val description: String
        )
    }
}