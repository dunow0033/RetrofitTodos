package com.example.retrofittodos.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.retrofittodos.ui.TodoAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittodos.R
import com.example.retrofittodos.api.ApiManager
import com.example.retrofittodos.databinding.FragmentTodosBinding
import com.example.retrofittodos.repository.TodoRepository
import com.example.retrofittodos.ui.viewmodel.TodoViewModel
import com.example.retrofittodos.ui.viewmodel.TodoViewModelFactory

class TodoFragment : Fragment() {

    private var _binding: FragmentTodosBinding? = null
    private val binding: FragmentTodosBinding get() = _binding!!

    private lateinit var todoAdapter: TodoAdapter

    private lateinit var todoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodosBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        todoViewModel = ViewModelProvider(
            this,
            TodoViewModelFactory(TodoRepository(ApiManager()))
        ).get(TodoViewModel::class.java)

        todoViewModel.todoInfo.observe(viewLifecycleOwner, Observer {
            todoAdapter.todos = it.data!!.subList(0, it.data.size - 1)
        })
    }

    private fun setupRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}