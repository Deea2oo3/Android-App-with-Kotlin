package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    private lateinit var editUsername: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editConfirmPassword: EditText
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editUsername = findViewById(R.id.editRegisterUsername)
        editEmail = findViewById(R.id.editRegisterEmail)
        editPassword = findViewById(R.id.editRegisterPassword)
        editConfirmPassword = findViewById(R.id.editConfirmPassword)
        button = findViewById(R.id.buttonRegister)
        textView = findViewById(R.id.textAlreadyHaveAnAccount)
        database = Database(this, "MyAppDB", null, 1)

        button.setOnClickListener {
            val username = editUsername.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val confirmPassword = editConfirmPassword.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(applicationContext, "Please fill ALL details", Toast.LENGTH_SHORT).show()
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(applicationContext, "The passwords are not the same. Make sure to enter the same password.", Toast.LENGTH_SHORT).show()
            } else if (!isPasswordValid(password)) {
                Toast.makeText(applicationContext, "Password is not valid.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Register Success", Toast.LENGTH_SHORT).show()
                database.register(username, email, password)
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }

        textView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    fun isPasswordValid(password: String): Boolean {
        if (password.length < 8) {
            return false
        } else {
            val cond1 : Boolean
            val cond2 : Boolean
            val cond3 : Boolean
            val cond4 : Boolean
            if (password.contains("[1-9]".toRegex())) {
                cond1 = true
            } else {
                cond1 = false
            }
            if (password.contains("[A-Z]".toRegex())) {
                cond2 = true
            } else {
                cond2 = false
            }
            if (password.contains("[a-z]".toRegex())) {
                cond3 = true
            } else {
                cond3 = false
            }
            if (password.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
                cond4 = true
            } else {
                cond4 = false
            }
            if (cond1 && cond2 && cond3 && cond4) {
                return true
            }
        }
        return false
    }
}