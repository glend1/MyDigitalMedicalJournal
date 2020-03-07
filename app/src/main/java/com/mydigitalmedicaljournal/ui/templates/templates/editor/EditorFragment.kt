package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Templates

class EditorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //TODO complete this fragment
        val root = inflater.inflate(R.layout.fragment_editor, container, false)
        val tv = root.findViewById<TextView>(R.id.text_editor)
        var template: Templates.Template? = null
        val data = arguments?.get("data")
        for (pos in Templates(context!!).data) {
            if (data == pos.id) {
                template = pos
            }
        }
        if (template != null) {
            tv.text = "this is ${template.name} editor: ${template.id}"
        } else {
            tv.text = "new template"
        }
        return root
    }
}