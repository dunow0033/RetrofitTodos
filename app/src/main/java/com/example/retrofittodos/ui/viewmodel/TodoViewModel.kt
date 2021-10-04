package com.example.retrofittodos.ui.viewmodel

import androidx.lifecycle.*
import com.example.retrofittodos.model.Todo
import com.example.retrofittodos.model.TodoList
import com.example.retrofittodos.repository.TodoRepository
import com.example.retrofittodos.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TodoViewModel(private val todoRepo: TodoRepository) : ViewModel() {

    private var _todoInfo: MutableLiveData<Resource<TodoList>> = MutableLiveData()
    val todoInfo: LiveData<Resource<TodoList>> get() = _todoInfo

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch {
            todoRepo.getTodos().collect {
                _todoInfo.postValue(it)
            }
        }
    }
}

class TodoViewModelFactory(
    private val todoRepo: TodoRepository):
    ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TodoViewModel(todoRepo) as T
        }
}