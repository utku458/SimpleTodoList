package com.example.myapplication.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.TodoModel

@Database(entities = [TodoModel::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun TodoDao(): TodoDao
}