package com.serhiymysyshyn.smartpethubapplication.logic.entities

data class FeedingHour (
    val feedingDayOfWeek: Int,      // 1 - Monday, 2 - Tuesday ...
    val feedingTimeHour: String,       // 12 - hour value
    val feedingTimeMinute: String,     // 45 - minute value
    val feedingType: String,        // D - dry food, W - water
    val foodCapacity: Int           // if W - 150 ml, if D - 150 g
)