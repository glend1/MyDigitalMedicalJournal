package com.mydigitalmedicaljournal.ui.templates.templates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.CustomDivider

class ManageTemplatesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_template_list, container, false)
        //val template = Template(context!!)
        //val viewAdapter = TemplateListAdapter(template, R.layout.list_item, findNavController())
        val templateList = root.findViewById<RecyclerView>(R.id.recycler)
        val itemDecoration = CustomDivider(context!!)
        templateList.addItemDecoration(itemDecoration)
        //templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            findNavController().navigate(R.id.editorFragment)
        }
        return root
    }
}
