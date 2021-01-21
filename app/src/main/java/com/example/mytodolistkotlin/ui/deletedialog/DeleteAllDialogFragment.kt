package com.example.mytodolistkotlin.ui.deletedialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.mytodolistkotlin.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteAllDialogFragment : DialogFragment() {

    private val viewModel: DeleteDialogViewModel by viewModels ()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(requireContext())
                    .setMessage(getString(R.string.order_confirmation))
                    .setPositiveButton(getString(R.string.ok)) { _,_ ->

                        viewModel.DeleteAll()

                    }
                    .create()

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//      viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//          viewModel.deleteEventChannel.collect { event ->
//              when (event){
//                  is DeleteDialogViewModel.DeleteEvent.DeleteTasks ->{
//
//                      val action = DeleteAllDialogFragment.(null, "Add Task")
//                      findNavController().navigate(action)
//                  }
//              }
//
//          }
//
//
//          }
      }

