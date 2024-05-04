package com.parivesh.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    var taskItems = MutableLiveData<List<TaskItem>>()

    init {
        taskItems.value = mutableListOf()
    }

    fun addTaskItem(taskItem: TaskItem) {
        val updatedList = taskItems.value!!.toMutableList()
        updatedList.add(taskItem)
        taskItems.postValue(updatedList)
    }

    fun updateTaskItem(taskItem: TaskItem) {
        val updatedList = taskItems.value!!.toMutableList()
        val index = updatedList.indexOfFirst { it.id == taskItem.id }
        if (index != -1) {
            updatedList[index] = taskItem
            taskItems.postValue(updatedList)
        }
    }

    fun setTaskItemCompleted(taskItem: TaskItem) {
        val updatedList = taskItems.value!!.toMutableList()
        val task = updatedList.find { it.id == taskItem.id }
        task?.completed = !taskItem.completed
        taskItems.postValue(updatedList)
    }

    fun deleteTaskItem(taskItem: TaskItem) {
        val updatedList = taskItems.value!!.toMutableList()
        updatedList.remove(taskItem)
        taskItems.postValue(updatedList)
    }
}
