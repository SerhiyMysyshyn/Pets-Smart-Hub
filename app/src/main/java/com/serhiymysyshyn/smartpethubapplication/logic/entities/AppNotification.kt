package com.serhiymysyshyn.smartpethubapplication.logic.entities

data class AppNotification(val type: String) {

    override fun toString() = "Current notification type is: $type"

}
