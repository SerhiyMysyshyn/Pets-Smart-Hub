package com.serhiymysyshyn.smartpethubapplication.logic.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.serhiymysyshyn.smartpethubapplication.databinding.AlertDialogSortLayoutBinding
import com.serhiymysyshyn.smartpethubapplication.logic.adapters.NotificationSortAdapter
import com.serhiymysyshyn.smartpethubapplication.ui.notification.NotificationViewModel

class NotificationSortDialog (context: Context, _notificationViewModel: NotificationViewModel) : Dialog(context), NotificationSortAdapter.OnItemClickListener {
    private lateinit var binding: AlertDialogSortLayoutBinding
    private var notificationViewModel: NotificationViewModel = _notificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AlertDialogSortLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chooseNotificationSortRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = NotificationSortAdapter(context, this@NotificationSortDialog)
        }

    }

    override fun onItemClick(position: Int) {
        notificationViewModel.setSortType(position)
        dismiss()
    }
}