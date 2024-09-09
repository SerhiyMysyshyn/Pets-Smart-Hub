package com.serhiymysyshyn.smartpethubapplication.logic.di

import com.serhiymysyshyn.smartpethubapplication.cache.sharedPreferences.PrefsManager
import com.serhiymysyshyn.smartpethubapplication.logic.adapters.FeedingScheduleAdapter
import com.serhiymysyshyn.smartpethubapplication.logic.adapters.FeedingTimeAdapter
import com.serhiymysyshyn.smartpethubapplication.logic.adapters.NotificationItemTypeAdapter
import com.serhiymysyshyn.smartpethubapplication.logic.adapters.NotificationSortAdapter
import com.serhiymysyshyn.smartpethubapplication.logic.authentication.FirebaseAuthImpl
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.FeedingScheduleRepository
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.HomeRepository
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.NotificationRepository
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.ProfileRepository
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.SplashScreenRepository
import com.serhiymysyshyn.smartpethubapplication.ui.feedingSchedule.FeedingScheduleActivity
import com.serhiymysyshyn.smartpethubapplication.ui.feedingSchedule.FeedingScheduleViewModel
import com.serhiymysyshyn.smartpethubapplication.ui.feedingSchedule.SetFeedingTimeBottomSheetFragment
import com.serhiymysyshyn.smartpethubapplication.ui.greeting.GreetingActivity
import com.serhiymysyshyn.smartpethubapplication.ui.home.HomeFragment
import com.serhiymysyshyn.smartpethubapplication.ui.home.HomeViewModel
import com.serhiymysyshyn.smartpethubapplication.ui.login.LoginActivity
import com.serhiymysyshyn.smartpethubapplication.ui.main.MainActivity
import com.serhiymysyshyn.smartpethubapplication.ui.notification.NotificationFragment
import com.serhiymysyshyn.smartpethubapplication.ui.notification.NotificationViewModel
import com.serhiymysyshyn.smartpethubapplication.ui.profile.ProfileFragment
import com.serhiymysyshyn.smartpethubapplication.ui.profile.ProfileViewModel
import com.serhiymysyshyn.smartpethubapplication.ui.settings.SettingsFragment
import com.serhiymysyshyn.smartpethubapplication.ui.settings.SettingsViewModel
import com.serhiymysyshyn.smartpethubapplication.ui.splash.SplashActivity
import com.serhiymysyshyn.smartpethubapplication.ui.splash.SplashViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, RepositoriesModule::class])
interface AppComponent {

    // Activities
    fun inject(loginActivity: LoginActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(greetingActivity: GreetingActivity)
    fun inject(splashActivity: SplashActivity)
    fun inject(feedingScheduleActivity: FeedingScheduleActivity)



    // Fragments
    fun inject(setFeedingTimeBottomSheetFragment: SetFeedingTimeBottomSheetFragment)
    fun inject(profileFragment: ProfileFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(notificationFragment: NotificationFragment)
    fun inject(settingsFragment: SettingsFragment)



    // ViewModels
    fun inject(splashViewModel: SplashViewModel)
    fun inject(feedingScheduleViewModel: FeedingScheduleViewModel)
    fun inject(profileViewModel: ProfileViewModel)
    fun inject(homeViewModel: HomeViewModel)
    fun inject(notificationViewModel: NotificationViewModel)
    fun inject(settingsViewModel: SettingsViewModel)



    // Repositories
    fun inject(splashScreenRepository: SplashScreenRepository)
    fun inject(feedingScheduleRepository: FeedingScheduleRepository)
    fun inject(profileRepository: ProfileRepository)
    fun inject(homeRepository: HomeRepository)
    fun inject(notificationRepository: NotificationRepository)



    // Others
    fun inject(sharedPreferences: PrefsManager)
    fun inject(feedingScheduleAdapter: FeedingScheduleAdapter)
    fun inject(feedingTimeAdapter: FeedingTimeAdapter)
    fun inject(firebaseAuthImpl: FirebaseAuthImpl)
    fun inject(notificationItemTypeAdapter: NotificationItemTypeAdapter)
    fun inject(notificationSortAdapter: NotificationSortAdapter)
}