package com.serhiymysyshyn.smartpethubapplication.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.serhiymysyshyn.smartpethubapplication.logic.repositories.ProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    val googleLogoutTask: MutableLiveData<Task<Void>?> = MutableLiveData()



    fun startLogoutGoogleAccountUser() {
//        CoroutineScope(Dispatchers.Main).launch {
//            //val result = executeAsyncOperation()
//            //updateUI(result)
//        }
        CoroutineScope(Dispatchers.Main).launch {
            googleLogoutTask.value = profileRepository.logoutGoogleAccountUser()
        }
    }
}