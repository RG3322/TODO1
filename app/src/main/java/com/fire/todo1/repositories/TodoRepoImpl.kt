package com.fire.todo1.repositories

import com.fire.todo1.database.TodoDatabase
import com.fire.todo1.database.TodoEntity
import kotlinx.coroutines.flow.Flow


class TodoRepoImpl(private val database : TodoDatabase): TodoRepo {

   // private val dao: database.todoDao = TODO()()


    override suspend fun getTodos(): Flow<List<TodoEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun addTodo(todo: TodoEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTodo(todo: TodoEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTodo(todo: TodoEntity) {
        TODO("Not yet implemented")
    }
}