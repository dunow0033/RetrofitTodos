package com.example.retrofittodos.api

class ApiManager {
    private val todoService: TodoService
    private val retrofit = RetrofitInstance.providesRetrofit()

    init {
        todoService = retrofit.create(TodoService::class.java)
    }

    suspend fun getTodos() = todoService.getTodos()
}