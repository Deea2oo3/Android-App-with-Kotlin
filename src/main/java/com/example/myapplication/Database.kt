package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val query = "CREATE TABLE users(username TEXT, email TEXT, password TEXT)"
        sqLiteDatabase.execSQL(query)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun register(username: String, email: String, password: String) {
        val contentValues = ContentValues().apply {
            put("username", username)
            put("email", email)
            put("password", password)
        }
        val database = writableDatabase
        database.insert("users", null, contentValues)
        database.close()
    }

    fun itExists(username: String, password: String) : Boolean {
        val database = readableDatabase
        val cursor = database.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", arrayOf(username, password))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }
}
