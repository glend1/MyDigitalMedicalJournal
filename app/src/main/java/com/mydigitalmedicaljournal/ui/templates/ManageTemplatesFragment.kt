package com.mydigitalmedicaljournal.ui.templates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.CustomDivider
import com.mydigitalmedicaljournal.ui.templates.dialog.NewTemplateDialog

class ManageTemplatesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_templates, container, false)
        val viewAdapter = TemplateListAdapter(TemplateList(requireContext()).get())
        val templateList = root.findViewById<RecyclerView>(R.id.template_recycler)
        val itemDecoration = CustomDivider(requireContext())
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            val newTemplate =
                NewTemplateDialog(
                    requireContext().getString(R.string.new_template),
                    requireContext().getString(R.string.new_template_instruction),
                    requireContext(),
                    container
                )
            newTemplate.setConfirm(R.string.save) { _, _ -> }
            newTemplate.show()
            newTemplate.disableAutoDismiss {
                if (newTemplate.save()) {
                    newTemplate.dismiss()
                    val bundle = bundleOf("data" to newTemplate.getTemplateId())
                    findNavController().navigate(R.id.editorFragment, bundle)
                }
            }
        }
        return root
    }
}
