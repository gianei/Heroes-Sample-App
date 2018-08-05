package com.glsebastiany.heroessample.repository.model

data class CharacterComicsResponse(
        val status: String,
        val data: Data
) {

    data class Data(
            val offset: Int,
            val limit: Int,
            val total: Int,
            val count: Int,
            val results: List<CharacterComicResponse>
    ) {
        data class CharacterComicResponse(
                val id: Int,
                val title: String,
                val description: String
        )
    }
}