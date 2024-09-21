package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    private lateinit var todo : CardView
    private lateinit var booksMovies : CardView
    private lateinit var grateful : CardView
    private lateinit var logout : CardView
    private lateinit var notes : CardView

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)

        todo = findViewById(R.id.cardTODO)
        booksMovies = findViewById(R.id.cardBooksAndMovies)
        grateful = findViewById(R.id.cardGrateful)
        logout = findViewById(R.id.cardLogout)
        notes = findViewById(R.id.cardNotes)

        todo.setOnClickListener {
            startActivity(Intent(this, TODOActivity::class.java))
        }

        booksMovies.setOnClickListener {
            startActivity(Intent(this, BooksAndMoviesActivity::class.java))
        }

        grateful.setOnClickListener {
            startActivity(Intent(this, GratefulActivity::class.java))
        }

        logout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        notes.setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }
    }
}