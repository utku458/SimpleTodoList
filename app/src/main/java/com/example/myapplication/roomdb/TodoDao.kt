package com.example.myapplication.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.TodoModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface TodoDao {

    @Query(value = "SELECT * FROM TodoModel")
    fun getAll():Flowable<List<TodoModel>>
    @Insert
    fun insert(todoModel: TodoModel):Completable

    @Delete
    fun delete(todoModel: TodoModel):Completable
}