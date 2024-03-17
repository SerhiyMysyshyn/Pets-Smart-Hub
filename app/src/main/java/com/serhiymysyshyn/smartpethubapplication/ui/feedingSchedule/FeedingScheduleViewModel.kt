package com.serhiymysyshyn.smartpethubapplication.ui.feedingSchedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhiymysyshyn.smartpethubapplication.logic.entities.FeedingHour

class FeedingScheduleViewModel: ViewModel() {
    val feedingScheduleList: MutableLiveData<MutableList<FeedingHour>> = MutableLiveData()

    fun addNewItemToCurrentList(newItem: FeedingHour) {
        var _feedingScheduleList = feedingScheduleList.value
        if (_feedingScheduleList != null) {
            _feedingScheduleList.add(newItem)
            feedingScheduleList.value = _feedingScheduleList!!
        }
    }

    fun deleteItemFromCurrentList() {

    }

    fun saveNewFeedingSchedule(newScheduleList: List<FeedingHour>) {

    }

    fun getFeedingSchedule() {
        val dbList = ArrayList<FeedingHour>()
        feedingScheduleList.value = dbList
    }

}
