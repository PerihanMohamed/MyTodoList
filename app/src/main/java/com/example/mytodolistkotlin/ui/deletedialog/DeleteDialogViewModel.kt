package com.example.mytodolistkotlin.ui.deletedialog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.mytodolistkotlin.data.local.TaskDao
import com.example.mytodolistkotlin.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DeleteDialogViewModel @ViewModelInject constructor(
  private val taskDao: TaskDao ,
  @ApplicationScope private val applicationScope: CoroutineScope
) :ViewModel() {



      fun DeleteAll() = applicationScope.launch {

        taskDao.DeleteCompletedDialogFra()
    }

}