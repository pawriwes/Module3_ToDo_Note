package com.parivesh.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parivesh.todolist.databinding.TaskItemCellBinding

class TaskItemAdapter(
    private var tasks: List<TaskItem>,
    private val clickListener: TaskItemClickListner
): RecyclerView.Adapter<TaskItemViewHolder>() {

    fun updateTasks(newTasks: List<TaskItem>) {
        tasks = newTasks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskItemCellBinding.inflate(inflater, parent, false)
        return TaskItemViewHolder(parent.context, binding, clickListener)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.bind(tasks[position])
    }
}
