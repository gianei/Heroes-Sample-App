package com.glsebastiany.heroessample.ui.heroes.detail

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.glsebastiany.heroessample.core.TestApp
import com.glsebastiany.heroessample.repository.model.CharactersResponse
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HeroDetailViewModelTest {
    private lateinit var testApp: TestApp

    @Before
    fun setup() {
        testApp = InstrumentationRegistry.getTargetContext().applicationContext as TestApp
    }

    @Test
    fun loadShouldAddItemsToAdapter() {

        val vm = HeroDetailViewModel(testApp)

        vm.initArguments(Intent().putExtra(HeroDetailViewModel.ARGUMENT_CHARACTER, CharactersResponse.Data.CharacterResponse(
                3,
                "name",
                "descr"
        )))

        vm.load()

        Thread.sleep(200)

        assertThat(vm.heroComicsAdapter.viewModels).hasSize(1) // size of mocked repository

    }
}