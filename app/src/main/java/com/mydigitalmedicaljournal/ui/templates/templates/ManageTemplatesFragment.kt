package com.mydigitalmedicaljournal.ui.templates.templates

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.dialog.TextBox
import com.mydigitalmedicaljournal.model.Templates

class ManageTemplatesFragment : Fragment() {

    private var add: TextBox? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_manage_templates, container, false)
        val templates = Templates(context!!)
        val viewAdapter = TemplateRecyclerAdapter(templates)
        val templateList = root.findViewById<RecyclerView>(R.id.template_manage_template)
        val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            val listener = DialogInterface.OnClickListener { _, _ ->
                viewAdapter.templates.data.add(Templates.Template(add!!.getText()))
                viewAdapter.templates.sort()
                viewAdapter.templates.save()
                viewAdapter.notifyDataSetChanged()
            }
            add = TextBox(
                "Add New Template",
                "Please type the name for the new template",
                "",
                listener,
                context!!
            )
            viewAdapter.notifyDataSetChanged()
            add?.show()
        }
        return root

    }
}

