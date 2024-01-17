package com.serhiymysyshyn.smartpethubapplication.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhiymysyshyn.smartpethubapplication.cache.sharedPreferences.PrefsManager

class SplashViewModel: ViewModel() {
    val isSavedUserExist: MutableLiveData<Boolean> = MutableLiveData()

    fun isSavedUserExist() {
        isSavedUserExist.postValue(PrefsManager().isRememberUser())
    }

}