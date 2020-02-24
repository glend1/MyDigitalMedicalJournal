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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_manage_list, container, false)
        val templates = Templates(context!!)
        val viewAdapter = TemplateListAdapter(templates, R.layout.list_manage)
        val templateList = root.findViewById<RecyclerView>(R.id.recycler)
        // TODO the divider here leaves a divider at the end
        val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            val add = TextBox(
                "Add New Template",
                "Please type the name for the new template",
                "",
                context!!
            )
            add.setListener(DialogInterface.OnClickListener { _, _ ->
                viewAdapter.json.data.add(Templates.Template(add.getText()))
                viewAdapter.json.sort()
                viewAdapter.json.save()
                viewAdapter.notifyDataSetChanged()
            })
            viewAdapter.notifyDataSetChanged()
            add.show()
        }
        return root

    }
}

