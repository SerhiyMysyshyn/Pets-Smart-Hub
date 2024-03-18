package com.serhiymysyshyn.smartpethubapplication.logic.di

import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.cache.sharedPreferences.PrefsManager
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: PetsSmartHubApplication) {

    @Provides
    fun providePetsSmartHubApplication(): PetsSmartHubApplication {
        return application
    }

    @Provides
    fun provideSharedPreferences(application: PetsSmartHubApplication) = PrefsManager(application.applicationContext)
}