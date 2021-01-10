package com.example.mytodolistkotlin.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolistkotlin.model.Tasks

class TaskAdapter() : RecyclerView.Adapter<TaskAdapter.TaskViewHolder> (PhotoComparator){

    class TaskViewHolder : RecyclerView.ViewHolder() {

    }
    companion object PhotoComparator : DiffUtil.ItemCallback<Tasks>() {
        override fun areItemsTheSame(oldItem: Tasks, newItem: Tasks): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
                oldItem: Tasks,
                newItem: Tasks
        ): Boolean {
            return oldItem == newItem
        }
    }



}