package com.serhiymysyshyn.smartpethubapplication.logic.di

import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.FeedingScheduleRepository
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.HomeRepository
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.ProfileRepository
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.SplashScreenRepository
import com.serhiymysyshyn.smartpethubapplication.ui.feedingSchedule.FeedingScheduleViewModel
import com.serhiymysyshyn.smartpethubapplication.ui.home.HomeViewModel
import com.serhiymysyshyn.smartpethubapplication.ui.profile.ProfileViewModel
import com.serhiymysyshyn.smartpethubapplication.ui.splash.SplashViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
//    @Provides
//    fun provideFeedingScheduleViewModel(application: PetsSmartHubApplication): FeedingScheduleViewModel {
//        return ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(FeedingScheduleViewModel::class.java)
//    }

    @Provides
    fun provideSplashViewModel(splashScreenRepository: SplashScreenRepository): SplashViewModel {
        return SplashViewModel(splashScreenRepository)
    }

    @Provides
    fun provideFeedingScheduleViewModel(feedingScheduleRepository: FeedingScheduleRepository): FeedingScheduleViewModel {
        return FeedingScheduleViewModel(feedingScheduleRepository)
    }

    @Provides
    fun provideHomeViewModel(homeRepository: HomeRepository): HomeViewModel {
        return HomeViewModel(homeRepository)
    }

    @Provides
    fun provideProfileViewModel(profileRepository: ProfileRepository): ProfileViewModel {
        return ProfileViewModel(profileRepository)
    }
}