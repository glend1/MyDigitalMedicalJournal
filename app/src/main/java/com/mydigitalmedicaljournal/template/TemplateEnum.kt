package com.mydigitalmedicaljournal.template

import android.content.Context
import android.view.View
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.*
import com.mydigitalmedicaljournal.template.fields.data.question.*
import com.mydigitalmedicaljournal.template.fields.editor.*
import com.mydigitalmedicaljournal.template.fields.editor.question.*
import com.mydigitalmedicaljournal.template.file.TemplateManager

enum class TemplateEnum(
    val id: Int,
    val editorLayout: Int,
    val canCreate: Boolean,
    val listName: Int,
    val className: Class<out GenericData>
) {
    //TODO default values
    //TODO no fields are for event logging
    NAME(0, R.layout.editor_name, false, R.string.name, DataName::class.java) {
        override fun createEditor(view: View, template: TemplateManager, position: Int?): EditorName {
            return EditorName(view, template, position)
        }
        override fun createData(context: Context): DataName {
            return DataName(context)
        }
    },
    TIME(1, R.layout.editor_time_format, false, R.string.date, DataTime::class.java) {
        override fun createEditor(view: View, template: TemplateManager, position: Int?): EditorTime {
            return EditorTime(view, template, position)
        }
        override fun createData(context: Context): DataTime {
            return DataTime(context)
        }
    },
    //TODO SIMPLE: boolean
    SIMPLE(2, R.layout.editor_simple, true, R.string.simple, DataSimple::class.java) {
        override fun createEditor(view: View, template: TemplateManager, position: Int?): EditorSimple {
            return EditorSimple(view, template, position)
        }
        override fun createData(context: Context): DataSimple {
            return DataSimple(context)
        }
    },
    //TODO CHECK: check box [multi]
    CHECK(3, R.layout.editor_check, true, R.string.check, DataCheck::class.java) {
        override fun createEditor(view: View, template: TemplateManager, position: Int?): EditorCheck {
            return EditorCheck(view, template, position)
        }

        override fun createData(context: Context): DataCheck {
            return DataCheck(context)
        }
    },
    //TODO RADIO: radio group [multi]
    RADIO(4, R.layout.editor_radio, true, R.string.radio, DataRadio::class.java) {
        override fun createEditor(view: View, template: TemplateManager, position: Int?): EditorRadio {
            return EditorRadio(view, template, position)
        }
        override fun createData(context: Context): DataRadio {
            return DataRadio(context)
        }
    },
    //TODO DESCRIPTION: big text
    DESCRIPTION(5, R.layout.editor_description, true, R.string.description, DataDescription::class.java) {
        override fun createEditor(view: View, template: TemplateManager, position: Int?): EditorDescription {
            return EditorDescription(view, template, position)
        }
        override fun createData(context: Context): DataDescription {
            return DataDescription(context)
        }
    },
    //TODO RATING: sliding bar
    RATING(6, R.layout.editor_rating, true, R.string.rating, DataRating::class.java) {
        override fun createEditor(view: View, template: TemplateManager, position: Int?): EditorRating {
            return EditorRating(view, template, position)
        }
        override fun createData(context: Context): DataRating {
            return DataRating(context)
        }
    },
    //TODO VALUE: integer [need more details here measurements/count]
    VALUE(7, R.layout.editor_value, true, R.string.value, DataValue::class.java) {
        override fun createEditor(view: View, template: TemplateManager, position: Int?): EditorValue {
            return EditorValue(view, template, position)
        }
        override fun createData(context: Context): DataValue {
            return DataValue(context)
        }
    },
    //TODO IMAGE: image map [need more details here]
    //TODO change this to true to make it visible in the list
    IMAGE(8, R.layout.editor_image, false, R.string.image, DataImage::class.java) {
        override fun createEditor(view: View, template: TemplateManager, position: Int?): EditorImage {
            return EditorImage(view, template, position)
        }
        override fun createData(context: Context): DataImage {
            return DataImage(context)
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
                if (it.canCreate) {
                    ns.add(it.listName)
                    nameList[it.listName] = it
                }
            }
            values = v.toTypedArray()
            namedString = ns.toTypedArray()
        }
    }
    abstract fun createEditor(view: View, template: TemplateManager, position: Int?) : GenericEditor
    abstract fun createData(context: Context): GenericData
}