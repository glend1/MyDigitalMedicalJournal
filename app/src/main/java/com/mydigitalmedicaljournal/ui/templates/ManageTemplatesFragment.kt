package com.mydigitalmedicaljournal.ui.templates

import android.content.Context
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
import com.mydigitalmedicaljournal.ui._generics.EmptyAdapter
import com.mydigitalmedicaljournal.ui.templates.dialog.NewTemplateDialog

class ManageTemplatesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_templates, container, false)
        val viewAdapter = Adapter(TemplateList(requireContext()).get(), requireContext())
        val templateList = root.findViewById<RecyclerView>(R.id.template_recycler)
        val itemDecoration = CustomDivider(requireContext())
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            val newTemplate =
                NewTemplateDialog(
                    requireContext().getString(R.string.new_value, requireContext().getString(R.string.template)),
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
    class Adapter(private var fileList: List<FileList>, context: Context) : EmptyAdapter() {
        override val message = context.getString(R.string.no_data, context.getString(R.string.template))
        override val layout = R.layout.list_item
        override fun getItemCount() = if (isEmpty()) { 1 } else { fileList.size }
        override fun isEmpty() = fileList.isEmpty()

        override fun bindView(view: View, position: Int) {
            val title = view.findViewById<TextView>(R.id.text)
            title.text = fileList[position].name
            view.setOnClickListener {
                val bundle = bundleOf("data" to fileList[position].id)
                view.findNavController().navigate(R.id.editorFragment, bundle)
            }
        }
    }
}
