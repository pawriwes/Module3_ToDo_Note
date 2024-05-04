package com.parivesh.todolist

interface TaskItemClickListner {
    fun edit(taskItem: TaskItem)
    fun complete(taskItem: TaskItem)
}

interface TaskChangeListener {
    fun onTaskChanged()
}
