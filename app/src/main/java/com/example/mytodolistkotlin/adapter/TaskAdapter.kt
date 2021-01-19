package com.example.mytodolistkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolistkotlin.databinding.ItemTaskBinding
import com.example.mytodolistkotlin.model.Tasks

class TaskAdapter(private val listener :onItemClickListener ) : ListAdapter < Tasks ,TaskAdapter.TaskViewHolder> (DiffUtillCallBack()) {

      inner class TaskViewHolder(private val itemTaskBinding: ItemTaskBinding) :
        RecyclerView.ViewHolder(itemTaskBinding.root) {

         init {
             itemTaskBinding.apply {
                 root.setOnClickListener {
                     val position = adapterPosition
                     if(position != RecyclerView.NO_POSITION){
                          val task = getItem(position)
                         listener.onitemClick(task)
                     }
                 }
                 checkBoxCompleted.setOnClickListener {
                     val position =adapterPosition
                     if(position != RecyclerView.NO_POSITION ) {
                         val task = getItem(position)
                         listener.onCheckBoxCompleted(task , checkBoxCompleted.isChecked)
                     }

                 }
             }
         }

        fun bind(tasks: Tasks) {

            itemTaskBinding.apply {

                checkBoxCompleted.isChecked = tasks.completed
                textViewName.text = tasks.name
                textViewName.paint.isStrikeThruText = tasks.completed
                labelPriority.isVisible = tasks.important

            }

        }

    }


    class DiffUtillCallBack : DiffUtil.ItemCallback<Tasks>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        val itemTaskBinding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(itemTaskBinding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
    interface onItemClickListener{
        fun onitemClick (task:Tasks)
        fun onCheckBoxCompleted(tasks: Tasks , boxchecked: Boolean)
    }
}



