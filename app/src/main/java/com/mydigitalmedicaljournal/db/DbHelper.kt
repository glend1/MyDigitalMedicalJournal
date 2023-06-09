package com.mydigitalmedicaljournal.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(context: Context) : SQLiteOpenHelper(context, DB_FILE_NAME, null, DB_VERSION) {
    //TODO this needs completing
    companion object {
        const val DB_FILE_NAME = "mdmj.db"
        const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("CREATE", db.toString())
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i("UPGRADE", db.toString())
    }

}