package com.mydigitalmedicaljournal.json

import android.content.Context
import com.google.common.io.Files
import java.io.File

class FileHelper(fileName: String, context: Context, directories: Array<String> = arrayOf()) {
    companion object {
        fun getFileList(context: Context, pathArray: Array<String>): Array<File> {
            val path = pathArray.joinToString("/")
            val folder = File("${context.filesDir}/$path")
            return if (folder.exists()) {
                folder.listFiles()!!
            } else {
                arrayOf()
            }
        }
    }

    //external storage
    //context.getExternalFilesDir(null)
    private val path = context.filesDir
    private var fullPath = File("$path${joinDirectories(directories)}/$fileName")
    private var file = Files.asByteSink(fullPath)
    private val charset = Charsets.UTF_8

    private fun joinDirectories(directories: Array<String>): String {
        return if (directories.isNotEmpty()) {
            "/${directories.joinToString("/")}"
        } else {
            ""
        }
    }

    fun exists(): Boolean {
        return fullPath.exists()
    }

    private fun makeDirs() {
        val path = fullPath.parentFile!!
        if (!path.exists()) {
            path.mkdir()
        }
    }

    fun write(text: String?) {
        makeDirs()
        file.write(text!!.toByteArray(charset))
    }

    fun read(): String {
        return fullPath.reader(charset).readText()
    }

    fun delete() {
        fullPath.delete()
        deleteEmptyFolders(fullPath.parentFile!!)
    }

    private fun deleteEmptyFolders(parent: File) {
        if (parent.list()!!.isEmpty()) {
            parent.delete()
            deleteEmptyFolders(parent.parentFile!!)
        }
    }
}