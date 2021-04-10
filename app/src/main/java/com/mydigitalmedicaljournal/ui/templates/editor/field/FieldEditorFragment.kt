package com.mydigitalmedicaljournal.ui.templates.editor.field

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.file.TemplateManager
import java.util.*

class FieldEditorFragment : Fragment() {
    private lateinit var enumType: TemplateEnum
    private lateinit var template: TemplateManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val position = arguments?.get("position") as Int?
        val filename = arguments?.get("filename") as UUID?
        val type = arguments?.get("type") as TemplateEnum?
        val layout = if (filename != null) {
            template = TemplateManager(requireContext(), filename)
            when {
                position != null -> {
                    val field = template.getData().getData(position)
                    enumType = field.type
                    enumType.editorLayout
                }
                type != null -> {
                    enumType = type
                    enumType.editorLayout
                }
                else -> {
                    0
                }
            }

        } else {
            0
        }
        val view = inflater.inflate(layout, container, false)
        (activity as? AppCompatActivity)?.supportActionBar?.title = requireContext().resources.getString(enumType.listName)
        enumType.createEditor(view, template, position)
        return view
    }

    fun save() {
    }
}