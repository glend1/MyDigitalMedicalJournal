package com.mydigitalmedicaljournal.model

import android.content.Context
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.json.JsonHelper
import com.mydigitalmedicaljournal.model.template.TemplateFormat
import com.google.gson.reflect.TypeToken
import java.util.*

class Template(name: String, context: Context) {
    //TODO finish this class
    private var template: MutableList<TemplateFormat>? = null
    private val type = object: TypeToken<MutableList<TemplateFormat>>(){}.type!!
    private val json = JsonHelper(type)
    private val file = FileHelper(name, context)
    /*companion object {
        fun getTemplate(id : UUID, context : Context): Template {
            /*template.
            json = JsonHelper(type)
            file = FileHelper(fileName, context)*/
        }
        fun getAllTemplate(): MutableList<TemplateFormat> {

        }
    }*/
    /*fun initList() {
        if (template == null) {
            template = mutableListOf()
        }
    }
    fun add(tf : TemplateFormat) {
        initList()
        template!!.add(tf)
    }
    fun save() {
        val text = json.convert(template)
        file.write(text)
    }*/
}