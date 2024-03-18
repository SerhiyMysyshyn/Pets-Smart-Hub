package com.serhiymysyshyn.smartpethubapplication.logic.repositories

import com.serhiymysyshyn.smartpethubapplication.cache.sharedPreferences.PrefsManager
import javax.inject.Inject

class SplashScreenRepository @Inject constructor(
    val sharedPreferences: PrefsManager
) {

    fun isFirstLaunch(): Boolean {
        return sharedPreferences.isFirstLaunch()
    }

}