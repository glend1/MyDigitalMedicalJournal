package com.mydigitalmedicaljournal.template

import android.view.View
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.editor.*

enum class TemplateEnum(val id: Int, val editorLayout: Int, val listName: Int) {
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
                    need more details here measurements/count
                image map
                    need more details here
                small text
                sliding bar
                drop down?
     */
    /*
    TODO need to standardise
        editor
        viewer
        data
        database connection
     */
    NAME(0, R.layout.editor_name, 0) {
        override fun createInstance(view: View): GenericEditor {
            return EditorName(view)
        }
    },
    TIME(1, R.layout.editor_time_format, 0) {
        override fun createInstance(view: View): GenericEditor {
            return EditorTime(view)
        }
    },
    TEST(2, R.layout.editor_test1, R.string.test1) {
        override fun createInstance(view: View): GenericEditor {
            return EditorTest(view)
        }
    },
    TEST2(3, R.layout.editor_test2, R.string.test2) {
        override fun createInstance(view: View): GenericEditor {
            return EditorTest2(view)
        }
    };
    companion object {
        lateinit var values: Array<TemplateEnum>
        lateinit var namedString: Array<Int>
        val layoutList = hashMapOf<Int, TemplateEnum>()
        init {
            val v = mutableListOf<TemplateEnum>()
            val ns = mutableListOf<Int>()
            values().forEach {
                v.add(it.id, it)
                layoutList[it.editorLayout] = it
                if (it.listName != 0) {
                    ns.add(it.listName)
                }
            }
            values = v.toTypedArray()
            namedString = ns.toTypedArray()
        }
    }
    abstract fun createInstance(view: View) : GenericEditor
}