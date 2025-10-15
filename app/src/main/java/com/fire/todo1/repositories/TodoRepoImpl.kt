package com.fire.todo1.repositories

import com.fire.todo1.database.TodoDatabase
import com.fire.todo1.database.TodoEntity
import kotlinx.coroutines.flow.Flow


class TodoRepoImpl(private val database : TodoDatabase): TodoRepo {

    private val dao = database.todoDao()

    override suspend fun getTodos(): Flow<List<TodoEntity>> {
        return dao.getTodos() as Flow<List<TodoEntity>>
    }

    override suspend fun addTodo(todo: TodoEntity) {
        dao.addTodo(todo)
    }

    override suspend fun updateTodo(todo: TodoEntity) {
        dao.updateTodo(todo)
    }

    override suspend fun deleteTodo(todo: TodoEntity) {
        dao.deleteTodo(todo)
    }
}