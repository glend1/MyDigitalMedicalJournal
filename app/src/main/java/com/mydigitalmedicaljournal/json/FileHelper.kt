package com.mydigitalmedicaljournal.json

import android.content.Context
import com.google.common.io.ByteSink
import com.google.common.io.Files
import java.io.File
import java.nio.charset.Charset

class FileHelper (name: String, context: Context) {

    //external storage
    //context.getExternalFilesDir(null)
    private val path = context.filesDir
    private val fullPath: File = File("${path}/${name}")
    private val file: ByteSink = Files.asByteSink(fullPath)
    private val charset: Charset = Charsets.UTF_8

    fun exists(): Boolean {
        return fullPath.exists()
    }

    fun write(text: String?) {
        file.write(text!!.toByteArray(charset))
    }

    fun read(): String {
        return fullPath.reader(charset).readText()
    }

}