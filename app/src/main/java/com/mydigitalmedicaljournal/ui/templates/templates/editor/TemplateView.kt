package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.template.TemplateFormat
import com.mydigitalmedicaljournal.model.template.Time
import com.mydigitalmedicaljournal.ui._generics.OptionDialog

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

    ADD(0, R.string.add, R.layout.editor_add){
        override fun setEvent(view: View, adapter: EditorAdapter) {
            view.setOnClickListener {
                val selectType = OptionDialog(
                    view.context.getString(R.string.New),
                    view.context.getString(R.string.New_Text),
                    getStringList(view.context),
                    view.context
                )
                selectType.setListener(DialogInterface.OnClickListener { _, _ ->
                    //TODO this is too easily broken
                    val template = getView(selectType.getSelected() + 2)
                    adapter.templateDefinition.add(
                        TemplateFormat(template!!)
                    )
                    adapter.notifyDataSetChanged()
                })
                selectType.show()
            }
        }
    },
    TIMETYPE(1, R.string.time_type, R.layout.editor_time_type) {
        override fun setEvent(view: View, adapter: EditorAdapter) {
            view.findViewById<RadioGroup>(R.id.time_type).setOnCheckedChangeListener { radioGroup: RadioGroup, i: Int ->
                //TODO set the event
                val selected = getSelected(radioGroup)
                val time = adapter.getTime()
                if (time != null) {
                    (time as Time).timeType = selected
                    Log.i("TEMPLATEVIEW", "UPDATE")
                } else {
                    adapter.templateDefinition.add(Time(TIMETYPE, selected))
                    Log.i("TEMPLATEVIEW", "ADD")
                }
                Log.i("TEMPLATEVIEW", (adapter.getTime() as Time).timeType.toString())
            }
        }
        private fun getSelected(radioGroup: RadioGroup): Int {
            return when (radioGroup.checkedRadioButtonId) {
                R.id.radioButton2 -> {
                    0
                }
                R.id.radioButton3 -> {
                    1
                }
                R.id.radioButton4 -> {
                    2
                }
                else -> -1
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
    };
    companion object {
        fun getView(pos: Int): TemplateView? {
            var view : TemplateView? = null
            for (v in values()) {
                if (v.id == pos) {
                    view = v
                }
            }
            return view
        }
        fun getStringList(context : Context) : MutableList<String> {
            //TODO This is too much work, this should only be done once
            val data = mutableListOf<String>()
            for (template in values()) {
                if (!(template == ADD || template == TIMETYPE)) {
                    data.add(template.getNameString(context))
                }
            }
            return data
        }
    }
    abstract fun setEvent(view: View, adapter: EditorAdapter)
    fun getNameString(context: Context) : String {
        return context.resources.getString(listName)
    }
}
