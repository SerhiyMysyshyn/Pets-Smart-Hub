package com.serhiymysyshyn.smartpethubapplication.ui.home

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.serhiymysyshyn.smartpethubapplication.R

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeFragmentView : View
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false)
        return homeFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeFragmentView.findViewById<LinearLayout>(R.id.addNewHubButton).setOnClickListener {
            homeFragmentView.findViewById<LinearLayout>(R.id.addHubLayout).visibility = View.GONE
            homeFragmentView.findViewById<LinearLayout>(R.id.mainControlPanel).visibility = View.VISIBLE
        }

    }

}