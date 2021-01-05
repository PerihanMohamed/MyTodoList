package com.example.mytodolistkotlin.ui.tasks

import android.app.assist.AssistStructure
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mytodolistkotlin.data.local.TaskDao

class TasksViewModel @ViewModelInject constructor(
        private val taskDao: TaskDao
) : ViewModel() {

        val tasks = taskDao.getAllList().asLiveData()


}