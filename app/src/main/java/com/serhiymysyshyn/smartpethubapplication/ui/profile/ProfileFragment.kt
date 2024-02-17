package com.serhiymysyshyn.smartpethubapplication.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.databinding.FragmentProfileBinding
import com.serhiymysyshyn.smartpethubapplication.logic.core.PicassoHelper

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding
    private lateinit var application: PetsSmartHubApplication

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        application = PetsSmartHubApplication.getInstance()

        PicassoHelper().loadDrawableToImageView(R.drawable.cat_dog_2, binding.imageView3)

        val appUser = application.firebaseCurrentUser()
        if (appUser != null) {
            appUser.photoUrl?.let {
                PicassoHelper().loadDrawableToImageView(it, binding.profileLogo)
            }
            binding.profileUsername.text = appUser.displayName
            binding.profileEmail.text = appUser.email
        }

        binding.logoutButton.setOnClickListener {
            application.logoutUser(requireActivity())
        }
    }

}