package com.example.mytodolistkotlin.ui.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytodolistkotlin.R
import com.example.mytodolistkotlin.adapter.TaskAdapter
import com.example.mytodolistkotlin.databinding.FragmentTasksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment :Fragment(R.layout.fragment_tasks) {

    private val viewModel : TasksViewModel by viewModels ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTasksBinding.bind(view)
        val taskadapter = TaskAdapter()
        binding.apply {
            recyclerviewTasks.apply {
                adapter = taskadapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }


        viewModel.tasks.observe(viewLifecycleOwner) {
            taskadapter.submitList(it)
        }





    }
}