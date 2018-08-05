package com.glsebastiany.heroessample.ui.heroes

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.glsebastiany.heroessample.core.TestApp
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HeroesViewModelTest{

    private lateinit var testApp: TestApp

    @Before
    fun setup() {
        testApp =  InstrumentationRegistry.getTargetContext().applicationContext as TestApp
    }

    @Test
    fun loadShouldAddItemsToAdapter() {

        //This is just a small test showing how to use a mocked repository with DI. Ideally the VM should have many more specific tests

        val vm = HeroesViewModel(testApp)

        vm.load()

        //On a real environment, a signaling system should be put in place to notify when the observer has finished
        Thread.sleep(200)

        assertThat(vm.heroesAdapter.viewModels).hasSize(1) // size of mocked repository

    }

}