package com.glsebastiany.heroessample

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.glsebastiany.heroessample.core.TestApp
import com.glsebastiany.heroessample.ui.heroes.HeroesViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before



/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var testApp: TestApp

    @Before
    fun setup() {
        testApp =  InstrumentationRegistry.getTargetContext().applicationContext as TestApp
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.glsebastiany.heroessample", appContext.packageName)
    }

    @Test(expected = NotImplementedError::class)
    fun useCharactersViewModel() {

        val vm = HeroesViewModel(testApp)

        vm.load()

    }
}
