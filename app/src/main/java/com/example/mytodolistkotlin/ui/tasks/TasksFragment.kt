package com.example.mytodolistkotlin.ui.tasks

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolistkotlin.R
import com.example.mytodolistkotlin.adapter.TaskAdapter
import com.example.mytodolistkotlin.data.local.SortOrder
import com.example.mytodolistkotlin.databinding.FragmentTasksBinding
import com.example.mytodolistkotlin.model.Tasks
import com.example.mytodolistkotlin.utils.exhaustive
import com.example.mytodolistkotlin.utils.onQueryTextChange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TasksFragment :Fragment(R.layout.fragment_tasks)  , TaskAdapter.onItemClickListener{

    private val viewModel : TasksViewModel by viewModels ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTasksBinding.bind(view)
        val taskadapter = TaskAdapter(this)
        binding.apply {
            recyclerviewTasks.apply {
                adapter = taskadapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)


            }

            ItemTouchHelper (object : ItemTouchHelper.SimpleCallback(0 ,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val item = taskadapter.currentList[viewHolder.adapterPosition]
                    viewModel.onSwipeCalled(item)
                } }).attachToRecyclerView(recyclerviewTasks)

            fabAddTask.setOnClickListener {
                viewModel.openNewTaskclick()
            }
        }


        viewModel.tasks.observe(viewLifecycleOwner) {
            taskadapter.submitList(it)
        }


        setHasOptionsMenu(true)

      viewLifecycleOwner.lifecycleScope.launchWhenStarted {
          viewModel.taskEvent.collect { event ->
              when(event){

                  is TasksViewModel.TaskEvent.NavigatetoAddTask ->{
                      val action = TasksFragmentDirections.actionTasksFragmentToAddEditFragment(null, "Add Task")
                      findNavController().navigate(action)

                  }
                  is TasksViewModel.TaskEvent.NavigatetoEditTaskEvent ->{
                      val action = TasksFragmentDirections.actionTasksFragmentToAddEditFragment(event.task , " Edit Task")
                      findNavController().navigate(action)

                  }


                  TasksViewModel.TaskEvent.DeleteDialogTasks -> {
                    val action = TasksFragmentDirections.actionGlobalDeleteAllDialogFragment()
                      findNavController().navigate(action)

                  }
              }.exhaustive


          }
      }




    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_tasks, menu)

        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChange{
                //update search query
            viewModel.searchQuery.value = it
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.sort_by_date_created -> {
               viewModel.sortOrderSelected(SortOrder.BY_DATE)
                true
            }
            R.id.sort_by_name -> {
                viewModel.sortOrderSelected(SortOrder.BY_NAME)
                true
            }
            R.id.hide_complete_tasks -> {
                viewModel.hideCompletedSelected(item.isChecked)
                item.isChecked = !item.isChecked
                true
            }
            R.id.delete_tasks -> {
                viewModel.DeleteAllDialog()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onitemClick(task: Tasks) {
        viewModel.onTaskSelected(task)
    }

    override fun onCheckBoxCompleted(tasks: Tasks, boxchecked: Boolean) {
        viewModel.onTaskCheckedChanged(tasks , boxchecked)
    }
}