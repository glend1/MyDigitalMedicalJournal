package com.mydigitalmedicaljournal.template.editor

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateDefinition
import com.mydigitalmedicaljournal.template.file.properties.Name
import com.mydigitalmedicaljournal.template.file.properties.TemplateFormat
import com.mydigitalmedicaljournal.template.file.properties.Test
import com.mydigitalmedicaljournal.template.file.properties.Time
import com.mydigitalmedicaljournal.ui._generics.dialogs.ClearKeyboardEditText
import com.mydigitalmedicaljournal.ui.templates.templates.editor.EditorAdapter

enum class TemplateView(val id: Int, private val listName: Int, val layout: Int) {
    //TODO create these types
    //TODO abstract these types
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
        lateinit var et: EditText
        override fun setup(view: View, adapter: EditorAdapter) {
            et = view.findViewById(R.id.editText)
            setEvent(adapter, view)
            setField(adapter.localData.name!!)
        }

        /*private fun keyboardBehaviour() {
            et.setOnFocusChangeListener{ v: View, hasFocus: Boolean ->
                if (hasFocus) {
                    Log.i("FOCUS", "true")
                    //TODO when edittext gets focus open a dialog to edit
                } else {
                    Log.i("FOCUS", "false")
                    val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }*/

        override fun getObj(): Name {
            return Name()
        }
        private fun setEvent(adapter: EditorAdapter, view: View) {
            //TODO this should call keyboardBehvaiour directly
            et.setOnFocusChangeListener{v: View, hasFocus: Boolean ->
                Log.i("FOCUS", hasFocus.toString())
                if (hasFocus) {
                    val add =
                        ClearKeyboardEditText(
                            v.context.getString(R.string.Rename),
                            v.context.getString(R.string.Rename_Template_Text),
                            et.text.toString(),
                            v.context!!,
                            view
                        )
                    add.setConfirm(DialogInterface.OnClickListener { _, _ ->
                        //TODO this isn't triggered regardless of behaviour
                        changeData(adapter.localData, add.getText())
                        v.clearFocus()
                    })
                    add.show()
                }
            }
            /*et.addTextChangedListener {
                adapter.localData.name = et.text.toString()
            }*/
        }
        private fun setField(text: String) {
            et.setText(text)
        }
        private fun changeData(localData: TemplateDefinition, text: String) {
            setField(text)
            localData.name = text
        }
    },
    TIMEFORMAT(1, R.string.time, R.layout.editor_time_format) {
        lateinit var rg: RadioGroup
        override fun setup(view: View, adapter: EditorAdapter) {
            rg = view.findViewById(R.id.time_format)
            setEvent(view, adapter)
            setData(adapter.localData)
        }
        override fun getObj(): TemplateFormat {
            return Time()
        }
        private fun setEvent(view: View, adapter: EditorAdapter) {
            rg.setOnCheckedChangeListener { radioGroup: RadioGroup, i: Int ->
                view.findViewById<RadioButton>(i).requestFocus()
                val data = getData(i)
                if (data != null) {
                    adapter.localData.time = getData(i)!!
                } else {
                    Log.i("TEST", "why null tho? $i")
                }
            }
        }

        private fun setData(localData: TemplateDefinition) {
            rg.check(localData.time!!.view)
        }

        private fun getData(selected: Int): Time.TimeFormat? {
            return Time.TimeFormat.getType(selected)
        }
    },
    TEST(2, R.string.test1, R.layout.editor_test1) {
        override fun setup(view: View, adapter: EditorAdapter) {
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
    abstract fun setup(view: View, adapter: EditorAdapter)
    abstract fun getObj(): TemplateFormat
    fun getNameString(context: Context) : String {
        return context.resources.getString(listName)
    }
}
