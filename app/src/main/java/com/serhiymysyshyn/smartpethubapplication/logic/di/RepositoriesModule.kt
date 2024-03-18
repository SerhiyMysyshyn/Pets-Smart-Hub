package com.serhiymysyshyn.smartpethubapplication.logic.di

import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.cache.sharedPreferences.PrefsManager
import com.serhiymysyshyn.smartpethubapplication.logic.authentication.FirebaseAuthImpl
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.FeedingScheduleRepository
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.HomeRepository
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.ProfileRepository
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.SplashScreenRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {
    @Provides
    fun provideSplashScreenRepository(prefsManager: PrefsManager): SplashScreenRepository {
        return SplashScreenRepository(prefsManager)
    }

    @Provides
    fun provideFeedingScheduleRepository(): FeedingScheduleRepository {
        return FeedingScheduleRepository()
    }

    @Provides
    fun provideFirebaseAuthImpl(application: PetsSmartHubApplication): FirebaseAuthImpl {
        return FirebaseAuthImpl(application)
    }

    @Provides
    fun provideHomeRepository(application: PetsSmartHubApplication): HomeRepository {
        return HomeRepository(application)
    }

    @Provides
    fun provideProfileRepository(firebaseAuthImpl: FirebaseAuthImpl): ProfileRepository {
        return ProfileRepository(firebaseAuthImpl)
    }
}