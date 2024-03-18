package com.serhiymysyshyn.smartpethubapplication.logic.repositories

import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.logic.entities.SmartHubV1SensorsData
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val application: PetsSmartHubApplication
) {

    fun checkIfCurrentSmartHubExist(hubId: String): Boolean {
        return true
    }

    fun getSmartHubData(): SmartHubV1SensorsData {
        return SmartHubV1SensorsData(
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