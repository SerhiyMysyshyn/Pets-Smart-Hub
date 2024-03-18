package com.serhiymysyshyn.smartpethubapplication.ui.feedingSchedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhiymysyshyn.smartpethubapplication.logic.entities.FeedingHour
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.FeedingScheduleRepository
import javax.inject.Inject

class FeedingScheduleViewModel @Inject constructor(
    private val feedingScheduleRepository: FeedingScheduleRepository
) : ViewModel() {
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
        feedingScheduleRepository.test()
    }

    fun getFeedingSchedule() {
        val dbList = ArrayList<FeedingHour>()
        feedingScheduleList.value = dbList
    }

}
