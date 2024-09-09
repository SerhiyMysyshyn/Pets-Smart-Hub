package com.serhiymysyshyn.smartpethubapplication.ui.notification

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication.Companion.appComponent
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.databinding.FragmentNotificationBinding
import com.serhiymysyshyn.smartpethubapplication.logic.adapters.NotificationAdapter
import com.serhiymysyshyn.smartpethubapplication.logic.core.PicassoHelper
import com.serhiymysyshyn.smartpethubapplication.logic.dialogs.NotificationSortDialog
import com.serhiymysyshyn.smartpethubapplication.logic.dialogs.NotificationTypeDialog
import com.serhiymysyshyn.smartpethubapplication.logic.entities.AppNotification
import com.serhiymysyshyn.smartpethubapplication.ui.notification.notificationInfo.NotificationDetailsActivity
import javax.inject.Inject

class NotificationFragment : Fragment() {

    companion object {
        fun newInstance() = NotificationFragment()
    }

    @Inject
    lateinit var viewModel: NotificationViewModel

    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        appComponent.inject(this@NotificationFragment)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        PicassoHelper().loadDrawableToImageView(R.drawable.cat_comfort_temp, binding.imageView8)

        viewModel.getSortType()
        viewModel.getItemType()

        val notificationLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.notificationRecyclerView.layoutManager = notificationLayoutManager

        notificationAdapter = NotificationAdapter()
        binding.notificationRecyclerView.adapter = notificationAdapter

        notificationAdapter.setOnClickListener(object :
            NotificationAdapter.OnClickListener {
            override fun onClick(position: Int, appNotification: AppNotification) {
                val intent = Intent(requireContext(), NotificationDetailsActivity::class.java)
                intent.putExtra("NOTIFICATION_DATA", appNotification)
                startActivity(intent)
            }
        })

        viewModel.getNotificationList()

        viewModel.notificationList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.notificationFragmentProgressBar.visibility = View.GONE
                binding.notificationRecyclerViewLayout.visibility = View.GONE

                binding.emptyNotificationListLayout.visibility = View.VISIBLE
            }

            if (it.isNotEmpty()) {
                binding.notificationFragmentProgressBar.visibility = View.GONE
                binding.emptyNotificationListLayout.visibility = View.GONE

                binding.notificationRecyclerViewLayout.visibility = View.VISIBLE

                notificationAdapter.updateList(it)
            }
        }

        viewModel.sortType.observe(viewLifecycleOwner) { sortType ->
            if (sortType != null) {
                binding.chooseNotificationSortButtonText.text = resources.getStringArray(R.array.notificationSortValues)[sortType]
            }
        }

        viewModel.itemType.observe(viewLifecycleOwner) { itemType ->
            if (itemType != null) {
                binding.chooseNotificationTypeButtonText.text = resources.getStringArray(R.array.notificationTypeValues)[itemType]
            }
        }

        binding.chooseNotificationSortButton.setOnClickListener {
            NotificationSortDialog(requireContext(), viewModel).apply {
                window!!.setBackgroundDrawableResource(android.R.color.transparent)
                show()
            }
        }

        binding.chooseNotificationTypeButton.setOnClickListener {
            NotificationTypeDialog(requireContext(), viewModel).apply {
                window!!.setBackgroundDrawableResource(android.R.color.transparent)
                show()
            }
        }
    }

}