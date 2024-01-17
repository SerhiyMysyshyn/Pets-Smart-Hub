package com.serhiymysyshyn.smartpethubapplication.cache.sharedPreferences;

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.NonNull
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication

class PrefsManager() {
    private val appContext: Context = PetsSmartHubApplication.getInstance()

    private val REMEMBER_USER = "remember_user"

    private val USER_EMAIL = "user_email"
    private val USER_PASSWORD = "user_password"

    private val prefsSetting = appContext.getSharedPreferences("smart_perts_prefs", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = prefsSetting.edit()

    fun rememberUser(remember: Boolean) {
        editor.putBoolean(REMEMBER_USER, remember)
        editor.apply()
    }

    fun isRememberUser(): Boolean? {
        return prefsSetting.getBoolean(REMEMBER_USER, false)
    }

    fun saveSecureUserEmail(email: String) {
        // encrypt string before
        editor.putString(USER_EMAIL, email)
        editor.apply()
    }

    fun getSecureUserEmail(): String? {
        // decrypt string before
        return prefsSetting.getString(USER_EMAIL, "")
    }

}
