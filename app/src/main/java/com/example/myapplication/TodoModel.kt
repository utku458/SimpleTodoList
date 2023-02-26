package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TodoModel(
    @ColumnInfo(name = "todo" )
    val todo:String

    ):java.io.Serializable{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}