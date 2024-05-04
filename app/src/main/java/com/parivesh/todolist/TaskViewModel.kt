package com.parivesh.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.UUID

class TaskViewModel: ViewModel() {
    var taskItems = MutableLiveData<List<TaskItem>>()

    init {
        taskItems.value = mutableListOf()
    }
    fun addTaskItem(taskItem: TaskItem) {
        val updatedList = taskItems.value!!.toMutableList()
        updatedList.add(taskItem)
        taskItems.postValue(updatedList)
    }
    fun updateTaskItem(id: UUID, name: String, description: String) {
        val updatedList = taskItems.value!!
        val taskItem = updatedList.find { it.id == id }!!
        taskItem.name = name
        taskItem.description = description
        taskItems.postValue(updatedList)
    }
    fun setTaskItemCompleted(taskItem: TaskItem) {
        val updatedList = taskItems.value!!
        val task = updatedList.find { it.id == taskItem.id }!!
        task.completed = !taskItem.completed
        taskItems.postValue(updatedList)
    }
}