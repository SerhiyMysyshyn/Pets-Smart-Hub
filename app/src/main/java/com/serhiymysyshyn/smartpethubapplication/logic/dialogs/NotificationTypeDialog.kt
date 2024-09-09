package com.serhiymysyshyn.smartpethubapplication.logic.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.serhiymysyshyn.smartpethubapplication.databinding.AlertDialogTypeLayoutBinding
import com.serhiymysyshyn.smartpethubapplication.logic.adapters.NotificationItemTypeAdapter
import com.serhiymysyshyn.smartpethubapplication.ui.notification.NotificationViewModel

class NotificationTypeDialog (context: Context, _notificationViewModel: NotificationViewModel) : Dialog(context), NotificationItemTypeAdapter.OnItemClickListener {
    private lateinit var binding: AlertDialogTypeLayoutBinding
    private var notificationViewModel: NotificationViewModel = _notificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AlertDialogTypeLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chooseNotificationTypeRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = NotificationItemTypeAdapter(context, this@NotificationTypeDialog)
        }

    }

    override fun onItemClick(position: Int) {
        notificationViewModel.setItemType(position)
        dismiss()
    }
}