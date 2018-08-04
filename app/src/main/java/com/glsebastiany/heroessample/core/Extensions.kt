package com.glsebastiany.heroessample.core

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import com.glsebastiany.heroessample.core.application.App
import com.glsebastiany.heroessample.core.di.ApplicationComponent
import java.util.*

fun Context.getApplicationComponent(): ApplicationComponent =
        (this.applicationContext as App).applicationComponent


fun Configuration.getDefaultLocale(): Locale {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        locales[0]
    } else {
        @Suppress("DEPRECATION")
        locale
    }
}
