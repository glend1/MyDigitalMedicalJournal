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
import com.mydigitalmedicaljournal.model.TemplateFormat
import com.mydigitalmedicaljournal.model.Templates
import com.mydigitalmedicaljournal.ui._generics.CustomDivider

class EditorFragment : Fragment() {

    private lateinit var templateName: Templates.Template
    private lateinit var templateDefinition:  MutableList<TemplateFormat>
    lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //TODO this should mostly only effect the template file
        val templateFound = getTemplateId()
        getTemplate(templateFound)
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
            // this should delete the reference in categories json too
            // this should delete the reference in templates json too
            Log.i("DELETE", "Delete clicked")
        }
    }

    private fun setupSaveButton() {
        root.findViewById<View>(R.id.save).setOnClickListener {
            //TODO set this action
            // this should update the name in templates json too
            Log.i("SAVE", "Save clicked")
        }
    }

    private fun initRecycler() {
        val templateParameters = root.findViewById<RecyclerView>(R.id.template)
        val itemDecoration = CustomDivider(context!!)
        templateParameters.addItemDecoration(itemDecoration)
        //TODO need some logic to get a TemplateFormat to pass it in
        /*
        templateData.template
            is the uuid which will be the filename for the json data
        open <uuid>.json convert it to a object or create an empty one
         */
        templateParameters.adapter = EditorAdapter(templateDefinition)
    }

    private fun setTemplateText() {
        val editText = root.findViewById<EditText>(R.id.editText)
        editText.setText(templateName.name)
    }

    private fun getTemplateId(): Boolean {
        val extra = arguments?.get("data")
        for (data in Templates(context!!).data) {
            if (extra == data.id) {
                templateName = data
                return true
            }
        }
        templateName = Templates.Template("")
        return false
    }

    private fun getTemplate(templateFound: Boolean) {
        if (templateFound) {
            //TODO get json from this value
            templateName.id
            templateDefinition
        } else {
            //TODO create empty template
            templateDefinition = mutableListOf()
        }
    }
}