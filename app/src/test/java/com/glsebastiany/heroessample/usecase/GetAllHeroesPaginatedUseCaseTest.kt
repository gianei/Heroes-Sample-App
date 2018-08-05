package com.glsebastiany.heroessample.usecase

import com.glsebastiany.heroessample.repository.marvel.MarvelApiRepository
import com.glsebastiany.heroessample.repository.model.CharactersResponse
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import com.google.common.truth.Truth.assertThat

class GetAllHeroesPaginatedUseCaseTest {

    @Mock
    private lateinit var mockedApi: MarvelApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private val ucLimit = 25

    private val mockedData0 = CharactersResponse(
            "status",
            CharactersResponse.Data(
                    0,
                    ucLimit,
                    50,
                    25,
                    listOf(
                            CharactersResponse.Data.CharacterResponse(
                                    1,
                                    "a",
                                    "b"
                            )
                    )
            )
    )

    private val mockedData1 = CharactersResponse(
            "status",
            CharactersResponse.Data(
                    ucLimit,
                    ucLimit,
                    50,
                    25,
                    listOf(
                            CharactersResponse.Data.CharacterResponse(
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

        val uc = GetAllHeroesPaginatedUseCase(mockedApi)

        Mockito.`when`(mockedApi.getHeroesPaginated(0, uc.limit))
                .thenReturn(Single.just(mockedData0))


        val testObserver = uc.execute(Unit)
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

        val uc = GetAllHeroesPaginatedUseCase(mockedApi)
        uc.offset = uc.limit

        Mockito.`when`(mockedApi.getHeroesPaginated(uc.limit, uc.limit))
                .thenReturn(Single.just(mockedData1))

        val testObserver = uc.execute(Unit)
                .test()

        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertValue { value -> value == mockedData1.data.results }

        assertThat(uc.offset).isEqualTo(ucLimit * 2)
        assertThat(uc.hasMorePages).isFalse()
    }

    @Test
    fun resetUcShouldResetPaging() {
        val uc = GetAllHeroesPaginatedUseCase(mockedApi)
        uc.offset = uc.limit

        uc.resetOffset()

        assertThat(uc.offset).isEqualTo(0)

    }

}