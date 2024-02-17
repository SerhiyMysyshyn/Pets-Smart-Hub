package com.serhiymysyshyn.smartpethubapplication

import android.app.Activity
import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.serhiymysyshyn.smartpethubapplication.debug.CustomTags
import com.serhiymysyshyn.smartpethubapplication.debug.Logger
import com.serhiymysyshyn.smartpethubapplication.ui.main.MainActivity


class PetsSmartHubApplication: Application() {

    companion object {
        private lateinit var mAuth: FirebaseAuth
        private lateinit var mInstance: PetsSmartHubApplication
        fun getInstance(): PetsSmartHubApplication = mInstance
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this@PetsSmartHubApplication

        mAuth = Firebase.auth
    }

    fun firebaseAuth(): FirebaseAuth {
        return mAuth
    }

    fun firebaseCurrentUser(): FirebaseUser? {
       return mAuth.currentUser
    }

    fun logoutUser(activity: Activity) {
        val googleSignInClient = GoogleSignIn.getClient(applicationContext, GoogleSignInOptions.DEFAULT_SIGN_IN)

        if (mAuth.currentUser != null) {
            if (googleSignInClient != null) {
                try {
                    mAuth.signOut()
                    googleSignInClient!!.signOut().addOnCompleteListener(activity) {
                        activity.finish()
                    }
                } catch (e: Exception) {
                    Logger.e(CustomTags.logout, "Logout error: ${e.message.toString()}")
                }
            } else {
                Logger.i(CustomTags.logout, "GoogleSignInClient is NULL")
            }
        } else {
            Logger.i(CustomTags.logout, "Current user is NULL")
        }
    }
}