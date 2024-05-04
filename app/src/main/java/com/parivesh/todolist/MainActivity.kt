package com.parivesh.todolist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.parivesh.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskItemClickListner, TaskChangeListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var adapter: TaskItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.newTaskBtn.setOnClickListener {
            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
        }
        setRecyclerView()
        loadDataFromDatabase()
    }
    private fun loadDataFromDatabase() {
        val db = DatabaseHandler(this)
        val taskItems = db.getAllNotes()
        taskViewModel.taskItems.value = taskItems
    }
    private fun setRecyclerView() {
        adapter = TaskItemAdapter(emptyList(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.adapter = adapter

        taskViewModel.taskItems.observe(this) { taskItems ->
            adapter.updateTasks(taskItems)
        }
    }

    override fun edit(taskItem: TaskItem) {
        val newTaskSheet = NewTaskSheet(taskItem)
        newTaskSheet.taskChangeListener = this
        newTaskSheet.show(supportFragmentManager, "newTaskTag")
    }

    override fun complete(taskItem: TaskItem) {
        taskViewModel.setTaskItemCompleted(taskItem)
    }

    override fun onTaskChanged() {
        adapter.notifyDataSetChanged()
    }
}