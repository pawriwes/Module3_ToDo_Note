package com.parivesh.todolist

import java.util.UUID

class TaskItem (
    var name: String = "",
    var description: String = "",
    var completed: Boolean = false,
    var id: String = UUID.randomUUID().toString()
){
    fun iconResource(): Int = if (completed) R.drawable.checked else R.drawable.unchecked
}