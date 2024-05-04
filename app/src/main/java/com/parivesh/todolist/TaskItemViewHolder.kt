package com.parivesh.todolist

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.parivesh.todolist.databinding.TaskItemCellBinding

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListner
): RecyclerView.ViewHolder(binding.root) {
    fun bind(task: TaskItem) {
        binding.taskName.text = task.name
        binding.taskDesc.text = task.description
        binding.completeButton.setImageResource(task.iconResource())

        binding.completeButton.setOnClickListener {
            clickListener.complete(task)
        }
        binding.taskName.setOnClickListener {
            clickListener.edit(task)
        }
        if (task.completed) {
            binding.taskName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.taskDesc.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            binding.taskName.paintFlags = binding.taskName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            binding.taskDesc.paintFlags = binding.taskDesc.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}