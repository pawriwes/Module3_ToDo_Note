package com.parivesh.todolist

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DB_NAME = "NotesDB"
const val DB_VERSION = 1
const val TABLE_NOTES = "Notes"
const val KEY_ID = "id"
const val KEY_TITLE = "title"
const val KEY_DESCRIPTION = "description"
const val KEY_IS_COMPLETED = "is_completed"
class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NOTES (" +
                "$KEY_ID TEXT PRIMARY KEY, " +
                "$KEY_TITLE TEXT, " +
                "$KEY_DESCRIPTION TEXT, " +
                "$KEY_IS_COMPLETED BOOLEAN)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun insertNote(task: TaskItem) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, task.id.toString())
        values.put(KEY_TITLE, task.name)
        values.put(KEY_DESCRIPTION, task.description)
        values.put(KEY_IS_COMPLETED, task.completed)
        db.insert(TABLE_NOTES, null, values)
        db.close()
    }
    fun getAllNotes(): List<TaskItem> {
        val notesList = ArrayList<TaskItem>()
        val selectQuery = "SELECT * FROM $TABLE_NOTES"
        val db = this.readableDatabase
        val results = db.rawQuery(selectQuery, null)
        if (results.moveToFirst()) {
            do {
                val task = TaskItem()
                task.id = results.getString(results.getColumnIndex(KEY_ID))
                task.name = results.getString(results.getColumnIndex(KEY_TITLE))
                task.description = results.getString(results.getColumnIndex(KEY_DESCRIPTION))
                task.completed = results.getInt(results.getColumnIndex(KEY_IS_COMPLETED)) == 1
                notesList.add(task)
            } while (results.moveToNext())
        }
        results.close()
        db.close()
        return notesList
    }
    fun updateNote(task: TaskItem) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(KEY_TITLE, task.name)
        values.put(KEY_DESCRIPTION, task.description)
        values.put(KEY_IS_COMPLETED, task.completed)
        db.update(TABLE_NOTES, values, "$KEY_ID=?", arrayOf(task.id))
        db.close()
    }
    fun deleteNote(task: TaskItem) {
        val db = writableDatabase
        db.delete(TABLE_NOTES, "$KEY_ID=?", arrayOf(task.id))
        db.close()
    }
}