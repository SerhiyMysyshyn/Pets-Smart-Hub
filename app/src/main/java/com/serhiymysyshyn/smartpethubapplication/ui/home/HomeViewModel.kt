package com.serhiymysyshyn.smartpethubapplication.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhiymysyshyn.smartpethubapplication.logic.entities.SmartHubV1SensorsData

class HomeViewModel : ViewModel() {
    val isSmartHubExist: MutableLiveData<Boolean> = MutableLiveData()
    val smartHubV1SensorsData: MutableLiveData<SmartHubV1SensorsData> = MutableLiveData()

    fun isSmartHubExist() {
        isSmartHubExist.value = true
    }

    fun getSmartHubSensorsData() {
        smartHubV1SensorsData.value = SmartHubV1SensorsData(
            hubId = "123456789",
            hubName = "Smart Hub V1",
            hubModel = "SmartHubV1",
            isLocked = false,
            wifiConnectionLevel = 3,
            batteryLevel = -1,
            waterLevel = 95,
            feedLevel = 50,
            airTemperature = 23.5,
            airHumidity = 99,
            airQuality = 100,
            dangerousGasDetected = false,
            co2InAir = 0,
            coAndCngGasInAir = 0,
            feedingSchedule = listOf(),
            hubSettings = listOf()
        )
    }
}