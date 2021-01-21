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

//     val AddEditTaskEventChanne = Channel <AddEditTaskEvent>()
//     val addEditTaskEventChanne = AddEditTaskEventChanne.receiveAsFlow()

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


    fun saveTask()  {
         if (TaskName.isBlank()){

         }
        if  (Task != null){

           val updateTask = Task.copy(name = TaskName , important =  TaskImportant)
            UpdateTask (updateTask)

        }else {
            val newTask = Tasks(name =TaskName ,important = TaskImportant)
            addNewTask(newTask)
        }




    }

//    private fun invalidInput(s: String) = viewModelScope.launch {
//        AddEditTaskEventChanne.send(AddEditTaskEvent.showUpMessage(s))
//    }

    private fun UpdateTask(task: Tasks) = viewModelScope.launch {
//       AddEditTaskEventChanne.send(AddEditTaskEvent.navigateBackWithAResult())
        taskDao.update(task)
    }

    private fun addNewTask(task: Tasks)  = viewModelScope.launch {
//        AddEditTaskEventChanne.send(AddEditTaskEvent.navigateBackWithAResult())
        taskDao.insert(task)
    }
//
//    sealed class AddEditTaskEvent(){
//        data class showUpMessage( val msg : String) : AddEditTaskEvent()
////        data class navigateBackWithAResult(val int: Int) : AddEditTaskEvent()
//
//
//    }


}