package com.serhiymysyshyn.smartpethubapplication.ui.settings

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.databinding.FragmentSettingsBinding
import com.serhiymysyshyn.smartpethubapplication.logic.core.PicassoHelper

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        PicassoHelper().loadDrawableToImageView(R.drawable.cat_settings_1, binding.imageViewSettings)

        binding.notificationSettingsButton.setOnClickListener {
            openNotificationSettingsInAllVersions(requireContext())
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                openNotificationSettings(requireContext())
//            } else {
//                openNotificationSettings_v2(requireContext())
//            }
        }
    }

    private fun openNotificationSettings(context: Context){
        val mManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel: NotificationChannel = mManager.getNotificationChannel(context.packageName)
            val intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName);
            startActivity(intent)
        }
    }

    private fun openNotificationSettingsInAllVersions(context: Context){
        val settingsIntent: Intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        startActivity(settingsIntent)
    }

}