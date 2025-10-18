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

/**
 * An extension property for the [TodoEntity] class.
 * It converts the 'added' timestamp (which is a Long representing milliseconds since the epoch)
 * into a human-readable date and time String.
 *
 * The format used is "yyyy/MM/dd hh:mm:ss" (e.g., "2023/10/27 03:15:55").
 *
 * @return A formatted date string representing when the to-do item was added.
 */
val TodoEntity.addDate: String @SuppressLint("SimpleDateFormat")
get()= SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(Date(added))
/**
 * Basic project entity, created the whole database table.
 *
 */