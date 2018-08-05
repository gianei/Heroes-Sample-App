package com.glsebastiany.heroessample.usecase

import com.glsebastiany.heroessample.repository.marvel.MarvelApiRepository
import com.glsebastiany.heroessample.repository.model.CharacterComicsResponse
import com.google.common.truth.Truth.assertThat
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetAllComicsPaginatedUseCaseTest {

    @Mock
    private lateinit var mockedApi: MarvelApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private val characterId = 0

    private val ucLimit = 25

    private val mockedData0 = CharacterComicsResponse(
            "status",
            CharacterComicsResponse.Data(
                    0,
                    ucLimit,
                    50,
                    25,
                    listOf(
                            CharacterComicsResponse.Data.CharacterComicResponse(
                                    1,
                                    "a",
                                    "b"
                            )
                    )
            )
    )

    private val mockedData1 = CharacterComicsResponse(
            "status",
            CharacterComicsResponse.Data(
                    ucLimit,
                    ucLimit,
                    50,
                    25,
                    listOf(
                            CharacterComicsResponse.Data.CharacterComicResponse(
                                    2,
                                    "b",
                                    "c"
                            )
                    )
            )
    )

    @Test
    fun loadFirstPageShouldReturnFirstPageAndHaveMore() {

        MockitoAnnotations.initMocks(this)

        val uc = GetAllComicsPaginatedUseCase(mockedApi)

        Mockito.`when`(mockedApi.getHeroComicsPaginated(characterId, 0, uc.limit))
                .thenReturn(Single.just(mockedData0))


        val testObserver = uc.execute(GetAllComicsPaginatedUseCase.Params(characterId))
                .test()

        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertValue { value -> value == mockedData0.data.results }

        assertThat(uc.offset).isEqualTo(ucLimit)
        assertThat(uc.hasMorePages).isTrue()
    }

    @Test
    fun loadSecondPageShouldReturnSecondPageAndDoNotHaveMore() {

        MockitoAnnotations.initMocks(this)

        val uc = GetAllComicsPaginatedUseCase(mockedApi)
        uc.offset = uc.limit

        Mockito.`when`(mockedApi.getHeroComicsPaginated(characterId, uc.limit, uc.limit))
                .thenReturn(Single.just(mockedData1))

        val testObserver = uc.execute(GetAllComicsPaginatedUseCase.Params(characterId))
                .test()

        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertValue { value -> value == mockedData1.data.results }

        assertThat(uc.offset).isEqualTo(ucLimit * 2)
        assertThat(uc.hasMorePages).isFalse()
    }

    @Test
    fun resetUcShouldResetPaging() {
        val uc = GetAllComicsPaginatedUseCase(mockedApi)
        uc.offset = uc.limit

        uc.resetOffset()

        assertThat(uc.offset).isEqualTo(0)

    }
}