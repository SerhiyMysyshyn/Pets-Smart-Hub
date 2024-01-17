package com.serhiymysyshyn.smartpethubapplication

import android.app.Application
import com.serhiymysyshyn.smartpethubapplication.ui.main.MainActivity

class PetsSmartHubApplication: Application() {

    companion object {
        private lateinit var mInstance: PetsSmartHubApplication

        fun getInstance(): PetsSmartHubApplication = mInstance

        private lateinit var mainActivity: MainActivity
    }

    override fun onCreate() {
        super.onCreate()
        println("[ SYSTEM ] Instance")

        mInstance = this@PetsSmartHubApplication

//        try {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        } catch (e: Exception) {
//            println("[ SYSTEM ] Dark mode not activated ($e) ")
//        }
//        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
//            println("[ SYSTEM ] Dark mode activated")
//        }
    }

    fun setMainActivity(_mainActivity: MainActivity) {
        mainActivity = _mainActivity
    }

    fun getMainActivity() = mainActivity

}