package com.glsebastiany.heroessample


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.glsebastiany.heroessample.ui.splash.SplashScreen
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SimpleInteractionTest {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<SplashScreen> = ActivityTestRule(SplashScreen::class.java)

    private val testHeroName = "Hero 3"
    private val comicsLabelText = "Comics:"
    private val testComicName = "Comic title hero 3"

    @Test
    fun simpleInteractionTest() {

        //Wait for app to open
        Thread.sleep(500)

        val textViewHeroNameInList = onView(allOf(
                withId(R.id.textViewName),
                isDisplayed()
        ))
        textViewHeroNameInList.check(matches(withText(testHeroName)))
        textViewHeroNameInList.perform(click())

        //Wait for new activity to open
        Thread.sleep(500)

        val textViewDetailHeroName = onView(allOf(
                withId(R.id.textViewName),

                isDisplayed()
        ))
        textViewDetailHeroName.check(matches(withText(testHeroName)))

        val textViewComicsLabel = onView(allOf(
                withId(R.id.textViewComics),
                isDisplayed()
        ))
        textViewComicsLabel.check(matches(withText(comicsLabelText)))

        val textViewComicName = onView(allOf(
                withId(R.id.textViewComicName),
                isDisplayed()
        ))
        textViewComicName.check(matches(withText(testComicName)))

    }

}
