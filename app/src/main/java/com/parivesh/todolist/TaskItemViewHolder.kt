package com.parivesh.todolist

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.parivesh.todolist.databinding.TaskItemCellBinding

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListner,
    private val dbHelper: DatabaseHandler
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(task: TaskItem) {
        binding.taskName.text = task.name
        binding.taskDesc.text = task.description
        binding.completeButton.setImageResource(task.iconResource())

        binding.completeButton.setOnClickListener {
            toggleCompleted(task)
        }
        binding.taskName.setOnClickListener {
            clickListener.edit(task)
        }
        binding.taskDesc.setOnClickListener {
            clickListener.edit(task)
        }
        updateStrikeThrough(task.completed)
    }

    private fun toggleCompleted(task: TaskItem) {
        task.completed = !task.completed
        updateTask(task)
        updateStrikeThrough(task.completed)
    }

    private fun updateTask(task: TaskItem) {
        dbHelper.updateNote(task)
    }

    private fun updateStrikeThrough(completed: Boolean) {
        val completedImage = if (completed) R.drawable.checked else R.drawable.unchecked
        binding.completeButton.setImageResource(completedImage)
        if (completed) {
            binding.taskName.paintFlags = binding.taskName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            binding.taskDesc.paintFlags = binding.taskDesc.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            binding.taskName.paintFlags = binding.taskName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            binding.taskDesc.paintFlags = binding.taskDesc.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}

