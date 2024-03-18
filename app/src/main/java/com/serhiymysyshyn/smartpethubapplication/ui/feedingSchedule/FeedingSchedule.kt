package com.serhiymysyshyn.smartpethubapplication.ui.feedingSchedule

import com.serhiymysyshyn.smartpethubapplication.logic.entities.FeedingHour

interface FeedingSchedule {

    fun addNewScheduleItem(scheduleItem: FeedingHour)

}