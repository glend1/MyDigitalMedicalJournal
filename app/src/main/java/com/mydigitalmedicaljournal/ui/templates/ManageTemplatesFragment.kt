package com.mydigitalmedicaljournal.ui.templates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.data.FileList
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.CustomDivider
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import com.mydigitalmedicaljournal.ui.templates.dialog.NewTemplateDialog

class ManageTemplatesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_templates, container, false)
        val viewAdapter = Adapter(TemplateList(requireContext()).get())
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

    class Adapter(private var fileList: List<FileList>) : RecyclerView.Adapter<ViewHolder>() {
        private fun isEmpty(): Boolean {
            return fileList.isEmpty()
        }

        override fun getItemCount() = if (isEmpty()) { 1 } else { fileList.size }

        override fun getItemViewType(position: Int): Int {
            return if (isEmpty()) { R.layout.empty_recycler } else { R.layout.list_item }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layout = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
            return ViewHolder(layout)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = holder.itemView
            if (isEmpty()) {
                item.findViewById<TextView>(R.id.message).text = item.resources.getString(R.string.no_templates)
            } else {
                val title = item.findViewById<TextView>(R.id.text)
                title.text = fileList[position].name
                item.setOnClickListener {
                    val bundle = bundleOf("data" to fileList[position].id)
                    item.findNavController().navigate(R.id.editorFragment, bundle)
                }
            }
        }
    }
}
