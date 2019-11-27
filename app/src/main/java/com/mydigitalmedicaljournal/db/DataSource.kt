package com.mydigitalmedicaljournal.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataSource(private val context: Context) {

    private lateinit var database: SQLiteDatabase
    private lateinit var dbHelper: SQLiteOpenHelper

    fun open() {
        dbHelper = DbHelper(context)
        database = dbHelper.writableDatabase
    }

    fun close() {
        dbHelper.close()
    }

}