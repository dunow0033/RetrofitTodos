package com.example.retrofittodos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittodos.databinding.ItemTodoBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.retrofittodos.model.Todo
import com.example.retrofittodos.model.TodoList

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    //private val todoList = mutableListOf<Todo>()

    private val diffCallback = object : DiffUtil.ItemCallback<Todo>() {
        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    var todos: List<Todo>
        get() = differ.currentList
        set(value) { differ.submitList(value)}

    override fun getItemCount() = todos.size

//    fun submitList(todos : List<Todo>) {
//        todoList.clear()
//        todoList.addAll(todos)
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            tvTitle.text = todo.title
            cbDone.isChecked = todo.completed
        }
    }

    class TodoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root)
}