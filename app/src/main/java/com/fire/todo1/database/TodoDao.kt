package com.fire.todo1.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {
    @Insert
    suspend fun insertTask(todo: TodoEntity)


    @Query("SELECT * FROM 'todos'")
    fun getTodos(): Flow<List<TodoEntity>>

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

    @Update
    fun updateTodo(todo: TodoEntity)

    @Insert
    fun addTodo(todo: TodoEntity)

}
/**
 * Basic data base dao. Using roomData base features.
 *
 * @constructor Create empty Todo dao
 */





