package com.example.mytodolistkotlin.ui.addedit

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytodolistkotlin.R
import com.example.mytodolistkotlin.databinding.FragmentAddEditTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditFragment: Fragment(R.layout.fragment_add_edit_task) {

    private val viewModel: AddEditViewMode by viewModels ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditTaskBinding.bind(view)

        binding.apply {
            editTextTaskName.setText(viewModel.TaskName)
            checkBoxImportant.isChecked = viewModel.TaskImportant
            checkBoxImportant.jumpDrawablesToCurrentState()
            textViewDateCreated.isVisible = viewModel.Task !=null
            textViewDateCreated.text = "Created: ${viewModel.Task?.createdDateFormat}"



        }

    }
}