package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TODOActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_todoactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        todoAdapter = TodoAdapter((mutableListOf()))

        val rvTodoItems = findViewById<RecyclerView>(R.id.rvTodoItems)
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        val btnAddTodo = findViewById<Button>(R.id.btnAddTodo)
        val btnDeleteAll = findViewById<Button>(R.id.btnDeleteTodos)

        btnAddTodo.setOnClickListener {
            val etTodoItem = findViewById<EditText>(R.id.etTodoItem)
            val todoItem = etTodoItem.text.toString()
            if (todoItem.isNotEmpty()) {
                val todo = Todo(todoItem)
                todoAdapter.addTodo(todo)
                etTodoItem.text.clear()
            }
        }

        btnDeleteAll.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}