package com.serhiymysyshyn.smartpethubapplication.logic.authentication

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.debug.CustomTags
import com.serhiymysyshyn.smartpethubapplication.debug.Logger
import javax.inject.Inject

class FirebaseAuthImpl @Inject constructor(
    private val application: PetsSmartHubApplication,
) : FirebaseAuth {

    override fun logoutFirebaseUserWithGoogleAccount(): Task<Void>? {
        val googleSignInClient = GoogleSignIn.getClient(application.baseContext, GoogleSignInOptions.DEFAULT_SIGN_IN)

        if (Firebase.auth.currentUser != null) {
            if (googleSignInClient != null) {
                try {
                    Firebase.auth.signOut()
                    return googleSignInClient?.signOut()
//                    googleSignInClient?.signOut()?.addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//                            if (requiredActivity != null) {
//                                try {
//                                    requiredActivity.startActivity(
//                                        Intent(
//                                            requiredActivity,
//                                            LoginActivity::class.java
//                                        ).setFlags(
//                                            Intent.FLAG_ACTIVITY_NEW_TASK
//                                        )
//                                    )
//                                    requiredActivity?.finish()
//                                } catch (e: Exception) {
//                                    Toast.makeText(
//                                        application.applicationContext,
//                                        "Error: ${e.message.toString()}", Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                            }
//                        } else {
//                            Toast.makeText(application.applicationContext,
//                                application.resources.getString(R.string.error_string), Toast.LENGTH_SHORT).show()
//                        }
//                    }
                } catch (e: Exception) {
                    Logger.e(CustomTags.logout, "Logout error: ${e.message.toString()}")
                    return null
                }
            } else {
                Logger.i(CustomTags.logout, "GoogleSignInClient is NULL")
                return null
            }
        } else {
            Logger.i(CustomTags.logout, "Current user is NULL")
            return null
        }
    }

    override fun logoutFirebaseUserWithFacebookAccount() {
        TODO("Not yet implemented")
    }

    override fun logoutFirebaseUSerWithEmail() {
        TODO("Not yet implemented")
    }


}