package com.serhiymysyshyn.smartpethubapplication.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhiymysyshyn.smartpethubapplication.logic.entities.SmartHub

class HomeViewModel : ViewModel() {
    val isSmartHubExist: MutableLiveData<Boolean> = MutableLiveData()
    val smartHubData: MutableLiveData<SmartHub> = MutableLiveData()

    fun isSmartHubExist() {
        isSmartHubExist.value = false
    }

    fun getSmartHubSensorsData() {

    }
}