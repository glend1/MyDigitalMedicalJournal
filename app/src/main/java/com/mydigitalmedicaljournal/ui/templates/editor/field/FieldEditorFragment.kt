package com.mydigitalmedicaljournal.ui.templates.editor.field

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.file.TemplateManager
import java.util.*

class FieldEditorFragment : Fragment() {
    private var enumType: TemplateEnum? = null
    private var template: TemplateManager? = null
    private var error: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val position = arguments?.get("position") as Int?
        val filename = arguments?.get("filename") as UUID?
        val type = arguments?.get("type") as TemplateEnum?
        val layout = getLayout(filename, position, type)
        return if (layout != null) {
            layoutSetup(inflater, layout, container, position)
        } else {
            layoutError(inflater, container)
        }
    }

    private fun layoutError(inflater: LayoutInflater, container: ViewGroup?): View {
        val view = inflater.inflate(R.layout.empty_recycler, container, false)
        view.findViewById<TextView>(R.id.message).text = error!!
        return view
    }

    private fun layoutSetup(inflater: LayoutInflater, layout: Int, container: ViewGroup?, position: Int?): View {
        val view = inflater.inflate(layout, container, false)
        (activity as? AppCompatActivity)?.supportActionBar?.title = requireContext().resources.getString(enumType!!.listName)
        enumType!!.createEditor(view, template!!, position)
        return view
    }

    private fun getLayout(filename: UUID?, position: Int?, type: TemplateEnum?): Int? {
        return if (filename != null) {
            template = TemplateManager(requireContext(), filename)
            setEnumType(position, type)
            enumType!!.editorLayout
        } else {
            error = requireContext().resources.getString(R.string.not_found, requireContext().resources.getString(R.string.filename))
            null
        }
    }

    private fun setEnumType(position: Int?, type: TemplateEnum?) {
        enumType = when {
            position != null -> {
                template!!.getData().getData(position).type
            }
            type != null -> {
                type
            }
            else -> {
                error = requireContext().resources.getString(R.string.not_found, requireContext().resources.getString(R.string.field_type))
                null
            }
        }
    }

}