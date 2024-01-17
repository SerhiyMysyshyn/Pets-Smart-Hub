package com.serhiymysyshyn.smartpethubapplication.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.databinding.ActivityMainBinding
import com.serhiymysyshyn.smartpethubapplication.ui.home.HomeFragment
import com.serhiymysyshyn.smartpethubapplication.ui.notification.NotificationFragment
import com.serhiymysyshyn.smartpethubapplication.ui.profile.ProfileFragment
import com.serhiymysyshyn.smartpethubapplication.ui.settings.SettingsFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val HOME_FRAGMENT_TAG = "HomeFragment"
    private val NOTIFICATION_FRAGMENT_TAG = "NotificationFragment"
    private val PROFILE_FRAGMENT_TAG = "ProfileFragment"
    private val SETTINGS_FRAGMENT_TAG = "SettingsFragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        replaceFragment(HomeFragment.newInstance(), HOME_FRAGMENT_TAG)

        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment.newInstance(), HOME_FRAGMENT_TAG)
                }
                R.id.navigation_notifications -> {
                    replaceFragment(NotificationFragment.newInstance(), NOTIFICATION_FRAGMENT_TAG)
                }
                R.id.navigation_profile -> {
                    replaceFragment(ProfileFragment.newInstance(), PROFILE_FRAGMENT_TAG)
                }
                R.id.navigation_settings -> {
                    replaceFragment(SettingsFragment.newInstance(), SETTINGS_FRAGMENT_TAG)
                }
                else -> {
                    replaceFragment(HomeFragment.newInstance(), HOME_FRAGMENT_TAG)
                }
            }
        }


    }

    private fun replaceFragment(
        fragment: Fragment,
        fragmentTag: String
    ) : Boolean {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment, fragmentTag)
            .commit()
        return true
    }
}