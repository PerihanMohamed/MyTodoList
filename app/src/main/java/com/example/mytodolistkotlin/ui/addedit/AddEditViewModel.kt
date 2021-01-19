package com.example.mytodolistkotlin.ui.addedit

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodolistkotlin.data.local.TaskDao
import com.example.mytodolistkotlin.model.Tasks
import kotlinx.coroutines.launch

class AddEditViewMode @ViewModelInject constructor (
        private val taskDao: TaskDao ,
        @Assisted private val state: SavedStateHandle
        ) : ViewModel() {



    val Task  = state.get<Tasks>("task")

    var TaskName = state.get<String>("TaskName") ?: Task?.name?:" "
       set(value) {
           field =value
           state.set("TaskName  " , TaskName)
       }

    var TaskImportant = state.get<Boolean>("TaskImportant") ?: Task ?.important?:false
        set(value) {
            field =value
            state.set("TaskImportant  " , TaskImportant)
        }
    

    fun saveTask(tasks: Tasks)  = viewModelScope.launch {
        taskDao.update(tasks)
    }



}