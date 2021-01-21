package com.example.mytodolistkotlin.ui.tasks

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.mytodolistkotlin.data.local.PerferenceManger
import com.example.mytodolistkotlin.data.local.SortOrder
import com.example.mytodolistkotlin.data.local.TaskDao
import com.example.mytodolistkotlin.model.Tasks
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TasksViewModel @ViewModelInject constructor(
        private val taskDao: TaskDao ,
        @Assisted private val stateHandle: SavedStateHandle ,
        private val perferenceManger: PerferenceManger
) : ViewModel() {

        val searchQuery = stateHandle.getLiveData("searchQuery " , "")
//        val sortOrder = MutableStateFlow(SortOrder.BY_DATE)
//        val hideComplete = MutableStateFlow(false)

         val TaskEventChannel = Channel<TaskEvent>()
         val taskEvent = TaskEventChannel.receiveAsFlow()

        val perferenceFlow = perferenceManger.PreferenceFlow

        private val tasksFlow = combine(
                searchQuery.asFlow(),
                perferenceFlow
        ) { query, filterPerferences ->
              Pair(query,filterPerferences)
        }.flatMapLatest { (query,filterPerferences) ->
                taskDao.getTasks(query, filterPerferences.sortOrder  , filterPerferences.hideComplete)
        }

        fun sortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
                perferenceManger.sortOrder(sortOrder)
        }

        fun hideCompletedSelected(hideCompleted :Boolean) = viewModelScope.launch {
                perferenceManger.hideCompletes(hideCompleted)
        }

    fun onTaskSelected(task: Tasks) = viewModelScope.launch {
        TaskEventChannel.send(TaskEvent.NavigatetoEditTaskEvent(task))
    }

    fun onTaskCheckedChanged(task: Tasks, isChecked : Boolean) = viewModelScope.launch {
        taskDao.update(task.copy(completed = isChecked) )
    }

    fun onSwipeCalled(task:  Tasks) = viewModelScope.launch {
        taskDao.delete(task)
    }

    fun openNewTaskclick() = viewModelScope.launch {
        TaskEventChannel.send(TaskEvent.NavigatetoAddTask)
    }

    fun DeleteAllDialog() = viewModelScope.launch {

    }


    val tasks = tasksFlow.asLiveData()

    sealed class TaskEvent{
        object NavigatetoAddTask : TaskEvent()
        data class NavigatetoEditTaskEvent ( val task: Tasks) : TaskEvent()
        data class ShowUndoDeleteTaskMessage( val task: Tasks) :TaskEvent()
        object DeleteTasks : TaskEvent()

    }

}
