package com.serhiymysyshyn.smartpethubapplication.ui.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serhiymysyshyn.smartpethubapplication.logic.entities.AppNotification
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.NotificationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
) : ViewModel() {
    val notificationList: MutableLiveData<List<AppNotification>> = MutableLiveData()
    val sortType: MutableLiveData<Int> = MutableLiveData()
    val itemType: MutableLiveData<Int> = MutableLiveData()

    fun getNotificationList() {
        notificationList.value = notificationRepository.getUserNotificationList()
    }

    fun getSortType() {
        CoroutineScope(Dispatchers.IO).launch {
            val itemSortValue = withContext(Dispatchers.Main) {
                notificationRepository.getSortType()
            }
            withContext(Dispatchers.Main) {
                sortType.value = itemSortValue
            }
        }
    }

    fun setSortType(selectedSortType: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                sortType.value = selectedSortType
            }
            notificationRepository.setNewSortType(selectedSortType)
        }
    }

    fun getItemType() {
        CoroutineScope(Dispatchers.IO).launch {
            val itemTypeValue = withContext(Dispatchers.Main) {
                notificationRepository.getItemType()
            }
            withContext(Dispatchers.Main) {
                itemType.value = itemTypeValue
            }
        }
    }

    fun setItemType(selectedItemType: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                itemType.value = selectedItemType
            }
            notificationRepository.setNewItemType(selectedItemType)
        }
    }

}