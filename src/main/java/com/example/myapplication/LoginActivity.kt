package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var editUsername: EditText
    private lateinit var editPassword: EditText
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editUsername = findViewById(R.id.editLoginUsername)
        editPassword = findViewById(R.id.editLoginPassword)
        button = findViewById(R.id.buttonLogin)
        textView = findViewById(R.id.textViewNewUser)
        database = Database(this, "MyAppDB", null, 1)

        button.setOnClickListener {
            val username = editUsername.text.toString()
            val password = editPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Please fill ALL details", Toast.LENGTH_SHORT).show()
            } else if (!database.itExists(username, password)) {
                Toast.makeText(applicationContext, "The user with this password doesn't exist. Try again or create an account.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
                val sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.apply()
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }

        textView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}