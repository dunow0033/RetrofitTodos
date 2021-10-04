package com.example.retrofittodos.api

import com.example.retrofittodos.model.Todo
import com.example.retrofittodos.model.TodoList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoService {

    @GET("/todos")
    suspend fun getTodos(
        @Query("title") title: String = "delectus aut autem",
        @Query("completed") completed: Boolean = false
    ): Response<TodoList>
}