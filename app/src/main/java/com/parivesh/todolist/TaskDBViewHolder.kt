package com.parivesh.todolist

//class TaskDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
//
//    override fun onCreate(db: SQLiteDatabase) {
//        val SQL_CREATE_ENTRIES =
//            "CREATE TABLE ${TaskContract.TaskEntry.TABLE_NAME} (" +
//                    "${TaskContract.TaskEntry._ID} INTEGER PRIMARY KEY," +
//                    "${TaskContract.TaskEntry.COLUMN_NAME_NAME} TEXT," +
//                    "${TaskContract.TaskEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
//                    "${TaskContract.TaskEntry.COLUMN_NAME_COMPLETED} INTEGER)"
//
//        db.execSQL(SQL_CREATE_ENTRIES)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        // Handle database upgrade if needed
//    }
//
//    companion object {
//        const val DATABASE_VERSION = 1
//        const val DATABASE_NAME = "Task.db"
//    }
//}
//
//object TaskContract {
//    object TaskEntry {
//        const val TABLE_NAME = "tasks"
//        const val _ID = "_id"
//        const val COLUMN_NAME_NAME = "name"
//        const val COLUMN_NAME_DESCRIPTION = "description"
//        const val COLUMN_NAME_COMPLETED = "completed"
//    }
//}