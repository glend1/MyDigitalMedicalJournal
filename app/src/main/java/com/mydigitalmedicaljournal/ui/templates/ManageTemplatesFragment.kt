package com.mydigitalmedicaljournal.ui.templates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.CustomDivider

class ManageTemplatesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_templates, container, false)
        val template = TemplateList.getTemplates(requireContext(), arrayOf("templates"))
        val viewAdapter =
            TemplateListAdapter(
                template,
                R.layout.list_item,
                findNavController()
            )
        val templateList = root.findViewById<RecyclerView>(R.id.template_recycler)
        val itemDecoration = CustomDivider(requireContext())
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            findNavController().navigate(R.id.editorFragment)
        }
        return root
    }
}
