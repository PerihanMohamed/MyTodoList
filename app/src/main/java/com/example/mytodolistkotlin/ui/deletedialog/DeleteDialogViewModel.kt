package com.example.mytodolistkotlin.ui.deletedialog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodolistkotlin.data.local.TaskDao
import kotlinx.coroutines.launch

class DeleteDialogViewModel @ViewModelInject constructor(
  private val taskDao: TaskDao
) :ViewModel() {



    fun DeleteAll() = viewModelScope.launch {

        taskDao.DeleteCompletedDialogFra()
    }

}