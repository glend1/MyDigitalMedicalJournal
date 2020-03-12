package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.View
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.TemplateFormat
import com.mydigitalmedicaljournal.ui._generics.OptionDialog

enum class TemplateView(val id: Int, private val visibleName: Int, val layout: Int) {
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
                    adapter.templateDefinition.add(TemplateFormat(selectType.getSelected() + 1))
                    adapter.notifyDataSetChanged()
                })
                selectType.show()
            }
        }
    },
    TEST1(1, R.string.test1, R.layout.editor_test1) {
        override fun setEvent(view: View, adapter: EditorAdapter) {
            //TODO set the event
            Log.i("EVENT1", "clicked")
        }
    },
    TEST2(2, R.string.test2, R.layout.editor_test2) {
        override fun setEvent(view: View, adapter: EditorAdapter) {
            //TODO set the event
            Log.i("EVENT2", "clicked")
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
                if (template != ADD) {
                    data.add(template.getNameString(context))
                }
            }
            return data
        }
    }
    abstract fun setEvent(view: View, adapter: EditorAdapter)
    fun getNameString(context: Context) : String {
        return context.resources.getString(visibleName)
    }
}
