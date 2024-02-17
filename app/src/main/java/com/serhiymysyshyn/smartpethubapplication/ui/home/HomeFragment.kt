package com.serhiymysyshyn.smartpethubapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.databinding.FragmentHomeBinding
import com.serhiymysyshyn.smartpethubapplication.logic.core.PicassoHelper
import com.serhiymysyshyn.smartpethubapplication.ui.hubCamera.HubCameraActivity

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        showAddHubLayout()

        binding.hubCameraButton.setOnClickListener {
            startActivity(Intent(activity, HubCameraActivity::class.java))
        }
    }

    private fun showAddHubLayout() {
        binding.addHubLayout.visibility = View.VISIBLE
        PicassoHelper().loadDrawableToImageView(R.drawable.cat_1, binding.imageView7)
        binding.addNewHubButton.setOnClickListener {
            binding.addHubLayout.visibility = View.GONE
            binding.mainControlPanel.visibility = View.VISIBLE
        }
    }

}