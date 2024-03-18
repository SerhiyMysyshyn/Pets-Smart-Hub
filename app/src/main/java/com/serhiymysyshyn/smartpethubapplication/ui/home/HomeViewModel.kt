package com.serhiymysyshyn.smartpethubapplication.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhiymysyshyn.smartpethubapplication.logic.entities.SmartHubV1SensorsData
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    val isSmartHubExist: MutableLiveData<Boolean> = MutableLiveData()
    val smartHubV1SensorsData: MutableLiveData<SmartHubV1SensorsData> = MutableLiveData()

    fun isSmartHubExist() {
        isSmartHubExist.value = homeRepository.checkIfCurrentSmartHubExist("12345")
    }

    fun getSmartHubSensorsData() {
        smartHubV1SensorsData.value = homeRepository.getSmartHubData()
    }
}