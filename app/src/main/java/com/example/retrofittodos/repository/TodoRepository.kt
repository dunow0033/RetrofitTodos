package com.example.retrofittodos.repository

import com.example.retrofittodos.api.ApiManager
import com.example.retrofittodos.utils.Resource
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class TodoRepository(private val apiManager: ApiManager) {
    fun getTodos() = flow {
        //emit(Resource.Loading)

        val resource = try {
            val response = apiManager.getTodos()
            if(response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.errorBody().toString())
            }
        } catch(ex: Exception) {
            Resource.Error(ex.toString())
        }
        emit(resource)
    }
}