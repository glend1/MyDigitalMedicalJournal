package com.mydigitalmedicaljournal.template

import android.view.View
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.data.*
import com.mydigitalmedicaljournal.template.editor.*

enum class TemplateEnum(
    val id: Int,
    val editorLayout: Int,
    val listName: Int,
    val className: Class<out GenericData>
) {
    /*TODO abstract these types
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
    NAME(0, R.layout.editor_name, 0, DataName::class.java) {
        override fun createEditor(view: View): EditorName {
            return EditorName(view)
        }
        override fun createData(): DataName {
            return DataName()
        }
    },
    TIME(1, R.layout.editor_time_format, 0, DataTime::class.java) {
        override fun createEditor(view: View): EditorTime {
            return EditorTime(view)
        }
        override fun createData(): DataTime {
            return DataTime()
        }
    },
    TEST(2, R.layout.editor_test1, R.string.test1, DataTest::class.java) {
        override fun createEditor(view: View): EditorTest {
            return EditorTest(view)
        }
        override fun createData(): DataTest {
            return DataTest()
        }
    },
    TEST2(3, R.layout.editor_test2, R.string.test2, DataTest2::class.java) {
        override fun createEditor(view: View): EditorTest2 {
            return EditorTest2(view)
        }
        override fun createData(): DataTest2 {
            return DataTest2()
        }
    };
    companion object {
        private var values: Array<TemplateEnum>
        var namedString: Array<Int>
        val layoutList = hashMapOf<Int, TemplateEnum>()
        val nameList = hashMapOf<Int, TemplateEnum>()
        init {
            val v = mutableListOf<TemplateEnum>()
            val ns = mutableListOf<Int>()
            values().forEach {
                v.add(it.id, it)
                layoutList[it.editorLayout] = it
                if (it.listName != 0) {
                    ns.add(it.listName)
                    nameList[it.listName] = it
                }
            }
            values = v.toTypedArray()
            namedString = ns.toTypedArray()
        }
    }
    abstract fun createEditor(view: View) : GenericEditor
    abstract fun createData(): GenericData
}