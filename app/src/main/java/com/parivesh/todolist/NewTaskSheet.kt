package com.parivesh.todolist

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.parivesh.todolist.databinding.FragmentNewTaskSheetBinding


class NewTaskSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel
    var taskChangeListener: TaskChangeListener? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        if (taskItem != null) {
            binding.title.text = "Edit Task"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(taskItem!!.name)
            binding.desc.text = editable.newEditable(taskItem!!.description)
        } else {
            binding.title.text = "New Task"
            binding.name.text = null
            binding.deleteBtn.visibility = View.GONE // Hide delete button
        }
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.saveBtn.setOnClickListener {
            saveAction()
        }
        binding.deleteBtn.setOnClickListener {
            deleteAction()
        }
    }

    private fun saveAction() {
        val db = DatabaseHandler(requireContext())
        val name = binding.name.text.toString()
        val desc = binding.desc.text.toString()
        if (taskItem == null) {
            val newTask = TaskItem(name, desc)
            taskViewModel.addTaskItem(newTask)
            db.insertNote(newTask)
        } else {
            taskItem!!.name = name
            taskItem!!.description = desc
            taskViewModel.updateTaskItem(taskItem!!)
            db.updateNote(taskItem!!)
        }
        taskChangeListener?.onTaskChanged() // Notify the listener
        dismiss()
    }
    private fun deleteAction() {
        taskViewModel.deleteTaskItem(taskItem!!)
        val db = DatabaseHandler(requireContext())
        db.deleteNote(taskItem!!)
        dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }
}
