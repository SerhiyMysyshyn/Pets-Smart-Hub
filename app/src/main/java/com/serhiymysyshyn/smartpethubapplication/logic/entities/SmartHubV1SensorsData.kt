package com.serhiymysyshyn.smartpethubapplication.logic.entities

data class SmartHubV1SensorsData(
    val hubId: String,
    val hubName: String,
    val hubModel: String,
    val isLocked: Boolean,
    val wifiConnectionLevel: Int,
    val batteryLevel: Int,
    val waterLevel: Int,
    val feedLevel: Int,
    val airTemperature: Double,
    val airHumidity: Int,
    val airQuality: Int,
    val dangerousGasDetected: Boolean,
    val co2InAir: Int,
    val coAndCngGasInAir: Int,
    val feedingSchedule: List<String>,
    val hubSettings: List<String>) {

}
