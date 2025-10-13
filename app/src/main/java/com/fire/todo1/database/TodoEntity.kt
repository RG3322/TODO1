package com.fire.todo1.database

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date


@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0 ,
    @ColumnInfo(name = "title")
    val title: String="",
    @ColumnInfo(name = "sub_title")
    val subTitle: String="",
    @ColumnInfo(name = "done")
    val done: Boolean=false,
    @ColumnInfo(name = "added")
    val added: Long=System.currentTimeMillis()
)
val TodoEntity.addDate: String @SuppressLint("SimpleDateFormat")
get()= SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(Date(added))