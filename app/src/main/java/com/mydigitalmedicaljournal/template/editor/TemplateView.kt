package com.mydigitalmedicaljournal.template.editor

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import androidx.core.widget.addTextChangedListener
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.properties.Name
import com.mydigitalmedicaljournal.template.file.properties.TemplateFormat
import com.mydigitalmedicaljournal.template.file.properties.Test
import com.mydigitalmedicaljournal.template.file.properties.Time
import com.mydigitalmedicaljournal.ui.templates.templates.editor.EditorAdapter

enum class TemplateView(val id: Int, private val listName: Int, val layout: Int) {
    //TODO create these types
    /*
        these refer to fields
            must contain only one of the following
                date
                date/time
                duration
                    this logic should be added to the parent view
            must contain only one
                save button
                    this logic should be added to the parent view
            must contain only one
                name
            optionally can contain one or many of the following
                radio group [multi]
                check box [multi]
                boolean
                big text
                integer
                    need more details here mesurements/count
                image map
                    need more details here
                small text
                sliding bar
                drop down?
     */
    NAME(0, R.string.name, R.layout.editor_name) {
        override fun setEvent(view: View, adapter: EditorAdapter) {
            val tv = view.findViewById<EditText>(R.id.editText)
            tv.addTextChangedListener {
                adapter.localData.name = tv.text.toString()
            }
        }
        override fun getObj(): Name {
            return Name()
        }
    },
    TIMEFORMAT(1, R.string.time, R.layout.editor_time_format) {
        override fun setEvent(view: View, adapter: EditorAdapter) {
            view.findViewById<RadioGroup>(R.id.time_format).setOnCheckedChangeListener { radioGroup: RadioGroup, i: Int ->
                adapter.localData.time = getSelected(i)!!
            }
        }
        override fun getObj(): TemplateFormat {
            return Time()
        }
        private fun getSelected(selected: Int): Time.TimeFormat? {
            //TODO this is frail
            return when (selected) {
                R.id.radioButton2 -> {
                    Time.TimeFormat.getType(0)
                }
                R.id.radioButton3 -> {
                    Time.TimeFormat.getType(1)
                }
                R.id.radioButton4 -> {
                    Time.TimeFormat.getType(2)
                }
                else -> null
            }
        }
    },
    TEST(2, R.string.test1, R.layout.editor_test1) {
        override fun setEvent(view: View, adapter: EditorAdapter) {
            //TODO set the event
            view.setOnClickListener {
                //TODO set the event
                Log.i("EVENT1", "clicked")
            }
        }
        override fun getObj(): Test {
            return Test()
        }
    };
    companion object {
        fun getView(pos: Int): TemplateView? {
            for (v in values()) {
                if (v.id == pos) {
                    return v
                }
            }
            return null
        }
        private var list: MutableList<String>? = null
        fun getStringList(context : Context) : MutableList<String> {
            //TODO This is too much work, this should only be done once
            if (list == null) {
                val data = mutableListOf<String>()
                for (template in values()) {
                    if (!(template == NAME || template == TIMEFORMAT)) {
                        data.add(template.getNameString(context))
                    }
                }
                list = data
            }
            return list as MutableList<String>
        }
    }
    abstract fun setEvent(view: View, adapter: EditorAdapter)
    abstract fun getObj(): TemplateFormat
    fun getNameString(context: Context) : String {
        return context.resources.getString(listName)
    }
}
