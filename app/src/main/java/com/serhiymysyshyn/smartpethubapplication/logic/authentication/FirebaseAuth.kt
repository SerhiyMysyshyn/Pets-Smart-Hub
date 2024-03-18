package com.serhiymysyshyn.smartpethubapplication.logic.authentication

import com.google.android.gms.tasks.Task

interface FirebaseAuth {
    fun logoutFirebaseUserWithGoogleAccount(): Task<Void>?

    fun logoutFirebaseUserWithFacebookAccount()

    fun logoutFirebaseUSerWithEmail()
}