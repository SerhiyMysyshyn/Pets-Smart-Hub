package com.serhiymysyshyn.smartpethubapplication.logic.repositories

import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.cache.sharedPreferences.PrefsManager
import com.serhiymysyshyn.smartpethubapplication.logic.entities.AppNotification
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val application: PetsSmartHubApplication,
    private val sharedPreferences: PrefsManager
) {

    fun getUserNotificationList(): List<AppNotification> {
        // get sort and item type
        val list = arrayListOf<AppNotification>()
        list.add(
            AppNotification(
            "Recommendation",
            "18.03.2024",
            "20:41",
            null,
            "Hello, friend",
            "Its my first notification! Say me hello)))))))"
        )
        )

        return list
    }

    fun getSortType(): Int {
        return sharedPreferences.getNotificationSortType()
    }

    fun setNewSortType(newSortType: Int) {
        sharedPreferences.setNotificationSortType(newSortType)
    }

    fun getItemType(): Int {
        return sharedPreferences.getNotificationItemType()
    }

    fun setNewItemType(newItemType: Int) {
        sharedPreferences.setNotificationItemType(newItemType)
    }

}