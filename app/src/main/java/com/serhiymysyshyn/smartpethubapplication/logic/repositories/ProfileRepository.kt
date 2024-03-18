package com.serhiymysyshyn.smartpethubapplication.logic.repositories

import com.google.android.gms.tasks.Task
import com.serhiymysyshyn.smartpethubapplication.logic.authentication.FirebaseAuthImpl
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val firebaseAuthImpl: FirebaseAuthImpl
) {

    fun logoutGoogleAccountUser(): Task<Void>? {
        return firebaseAuthImpl.logoutFirebaseUserWithGoogleAccount()
    }
}