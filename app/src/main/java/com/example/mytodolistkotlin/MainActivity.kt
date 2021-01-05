package com.example.mytodolistkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytodolistkotlin.ui.tasks.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}