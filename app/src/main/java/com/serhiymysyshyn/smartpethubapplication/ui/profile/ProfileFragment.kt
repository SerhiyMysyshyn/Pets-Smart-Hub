package com.serhiymysyshyn.smartpethubapplication.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication.Companion.appComponent
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.databinding.FragmentProfileBinding
import com.serhiymysyshyn.smartpethubapplication.logic.core.PicassoHelper
import com.serhiymysyshyn.smartpethubapplication.ui.login.LoginActivity
import javax.inject.Inject

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    @Inject
    lateinit var viewModel: ProfileViewModel

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        appComponent.inject(this@ProfileFragment)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        PicassoHelper().loadDrawableToImageView(R.drawable.cat_dog_2, binding.imageView3)

        val appUser = Firebase.auth.currentUser
        if (appUser != null) {
            appUser.photoUrl?.let {
                PicassoHelper().loadDrawableToImageView(it, binding.profileLogo)
            }
            binding.profileUsername.text = appUser.displayName
            binding.profileEmail.text = appUser.email
        }

        viewModel.googleLogoutTask.observe(viewLifecycleOwner) {
            if (it != null) {
                it.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        try {
                            startActivity(
                                Intent(
                                    requireActivity(),
                                    LoginActivity::class.java
                                ).setFlags(
                                    Intent.FLAG_ACTIVITY_NEW_TASK
                                )
                            )
                            requireActivity()?.finish()
                        } catch (e: Exception) {
                            Toast.makeText(
                                requireContext(),
                                "Error: ${e.message.toString()}", Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), requireContext().resources.getString(R.string.error_string), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.logoutButton.setOnClickListener {
            viewModel.startLogoutGoogleAccountUser()
        }
    }



}