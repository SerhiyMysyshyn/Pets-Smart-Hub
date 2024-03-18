package com.serhiymysyshyn.smartpethubapplication.ui.splash

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhiymysyshyn.smartpethubapplication.cache.sharedPreferences.PrefsManager
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.SplashScreenRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val splashScreenRepository: SplashScreenRepository
) : ViewModel() {

    val isSavedUserExist: MutableLiveData<Boolean> = MutableLiveData()
    val isFirstLaunch: MutableLiveData<Boolean> = MutableLiveData()

    fun isFirstLaunch() {
        isFirstLaunch.postValue(splashScreenRepository.isFirstLaunch())
    }

//    fun isSavedUserExist() {
//        isSavedUserExist.postValue(PrefsManager().isRememberUser())
//    }
}