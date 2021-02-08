package com.mydigitalmedicaljournal.template

import android.view.View
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.*
import com.mydigitalmedicaljournal.template.fields.editor.*

enum class TemplateEnum(
    val id: Int,
    val editorLayout: Int,
    val listName: Int,
    val className: Class<out GenericData>
) {
    //TODO default values
    //TODO no fields are for event logging
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
    //TODO SIMPLE: boolean
    SIMPLE(2, R.layout.editor_simple, R.string.simple, DataSimple::class.java) {
        override fun createEditor(view: View): EditorSimple {
            return EditorSimple(view)
        }
        override fun createData(): DataSimple {
            return DataSimple()
        }
    },
    //TODO CHECK: check box [multi]
    CHECK(3, R.layout.editor_check, R.string.check, DataCheck::class.java) {
        override fun createEditor(view: View): EditorCheck {
            return EditorCheck(view)
        }

        override fun createData(): DataCheck {
            return DataCheck()
        }
    },
    //TODO RADIO: radio group [multi]
    RADIO(4, R.layout.editor_radio, R.string.radio, DataRadio::class.java) {
        override fun createEditor(view: View): EditorRadio {
            return EditorRadio(view)
        }
        override fun createData(): DataRadio {
            return DataRadio()
        }
    },
    //TODO DESCRIPTION: big text
    DESCRIPTION(5, R.layout.editor_description, R.string.description, DataDescription::class.java) {
        override fun createEditor(view: View): EditorDescription {
            return EditorDescription(view)
        }
        override fun createData(): DataDescription {
            return DataDescription()
        }
    },
    //TODO TEXT: small text
    TEXT(6, R.layout.editor_text, R.string.text, DataText::class.java) {
        override fun createEditor(view: View): EditorText {
            return EditorText(view)
        }
        override fun createData(): DataText {
            return DataText()
        }
    },
    //TODO RATING: sliding bar
    RATING(7, R.layout.editor_rating, R.string.rating, DataRating::class.java) {
        override fun createEditor(view: View): EditorRating {
            return EditorRating(view)
        }
        override fun createData(): DataRating {
            return DataRating()
        }
    },
    //TODO VALUE: integer [need more details here measurements/count]
    VALUE(8, R.layout.editor_value, R.string.value_label, DataValue::class.java) {
        override fun createEditor(view: View): EditorValue {
            return EditorValue(view)
        }
        override fun createData(): DataValue {
            return DataValue()
        }
    },
    //TODO IMAGE: image map [need more details here]
    IMAGE(9, R.layout.editor_image, R.string.image, DataImage::class.java) {
        override fun createEditor(view: View): EditorImage {
            return EditorImage(view)
        }
        override fun createData(): DataImage {
            return DataImage()
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