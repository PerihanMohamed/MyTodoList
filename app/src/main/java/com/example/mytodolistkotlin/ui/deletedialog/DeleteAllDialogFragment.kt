package com.example.mytodolistkotlin.ui.deletedialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DeleteAllDialogFragment : DialogFragment() {

    private val viewModel: DeleteDialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(requireContext())
                    .setTitle("Confirm deletion")
                    .setMessage("Do you really want to delete all completed tasks?")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel.DeleteAll()
                    }
                    .create()
}

