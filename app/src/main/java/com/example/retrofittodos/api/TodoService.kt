package com.example.retrofittodos.api

import com.example.retrofittodos.model.Todo
import retrofit2.Response
import retrofit2.http.GET

interface TodoService {

    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>
}