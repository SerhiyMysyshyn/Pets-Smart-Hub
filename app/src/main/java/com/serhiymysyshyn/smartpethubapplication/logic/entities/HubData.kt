package com.serhiymysyshyn.smartpethubapplication.logic.entities

data class HubData(
    val wifiConnectionLevel: Int,
    val batteryLevel: Int,
    val waterLevel: Int,
    val feedLevel: Int) {

}
