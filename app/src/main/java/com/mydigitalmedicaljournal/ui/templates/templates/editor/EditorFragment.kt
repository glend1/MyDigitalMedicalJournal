package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Templates
import com.mydigitalmedicaljournal.ui._generics.CustomDivider

class EditorFragment : Fragment() {

    var templateData: Templates.Template? = null
    lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setTemplateData()
        root = inflater.inflate(R.layout.fragment_editor, container, false)
        setTemplateText()
        initRecycler()
        setupSaveButton()
        setupDeleteButton()
        return root
    }

    private fun setupDeleteButton() {
        root.findViewById<View>(R.id.delete).setOnClickListener {
            //TODO set this action
            Log.i("DELETE", "Delete clicked")
        }
    }

    private fun setupSaveButton() {
        root.findViewById<View>(R.id.save).setOnClickListener {
            //TODO set this action
            Log.i("SAVE", "Save clicked")
        }
    }

    private fun initRecycler() {
        val templateParameters = root.findViewById<RecyclerView>(R.id.template)
        val itemDecoration = CustomDivider(context!!)
        templateParameters.addItemDecoration(itemDecoration)
        templateParameters.adapter = EditorAdapter()
    }

    private fun setTemplateText() {
        val editText = root.findViewById<EditText>(R.id.editText)
        if (templateData != null) {
            editText.setText(templateData!!.name)
        }
    }

    private fun setTemplateData() {
        val data = arguments?.get("data")
        for (pos in Templates(context!!).data) {
            if (data == pos.id) {
                templateData = pos
                break
            }
        }
    }
}