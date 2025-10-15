package com.fire.todo1.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TodoDao {
    @Insert
    suspend fun insertTask(todo: TodoEntity)


    @Query("SELECT * FROM 'todos'")
    suspend fun getTodos(): List<TodoEntity>

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

   @Update
   fun updateTodo(todo: TodoEntity)

   @Insert
   fun addTodo(todo: TodoEntity)







}