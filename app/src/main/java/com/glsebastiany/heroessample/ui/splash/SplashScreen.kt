package com.glsebastiany.heroessample.ui.splash

import android.support.v7.app.AppCompatActivity
import com.glsebastiany.heroessample.ui.heroes.HeroesActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        HeroesActivity.launch(this)
    }
}
