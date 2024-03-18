package com.serhiymysyshyn.smartpethubapplication

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.serhiymysyshyn.smartpethubapplication.logic.di.AppComponent
import com.serhiymysyshyn.smartpethubapplication.logic.di.AppModule
import com.serhiymysyshyn.smartpethubapplication.logic.di.DaggerAppComponent
import javax.inject.Inject


class PetsSmartHubApplication @Inject constructor() : Application() {

    companion object {
        private lateinit var mInstance: PetsSmartHubApplication
        lateinit var appComponent: AppComponent

        fun getInstance(): PetsSmartHubApplication = mInstance
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        mInstance = this@PetsSmartHubApplication
    }
}