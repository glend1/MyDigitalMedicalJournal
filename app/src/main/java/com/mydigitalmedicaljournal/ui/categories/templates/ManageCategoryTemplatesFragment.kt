package com.mydigitalmedicaljournal.ui.categories.templates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Categories
import com.mydigitalmedicaljournal.template.data.FileList
import com.mydigitalmedicaljournal.template.data.TemplatesAsMap
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.CustomDivider
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import java.util.*

class ManageCategoryTemplatesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val categories = Categories(requireContext())
        val position = arguments?.get("categoryPosition") as Int
        val category = categories.getCategory(position)
        val root = inflater.inflate(R.layout.fragment_manage_templates, container, false)
        val viewAdapter = Adapter(TemplateList(requireContext()).get(), category.templates)
        val templateList = root.findViewById<RecyclerView>(R.id.recycler)
        val itemDecoration = CustomDivider(requireContext())
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        val save = root.findViewById<ConstraintLayout>(R.id.save)
        save.setOnClickListener {
            if (!viewAdapter.isEmpty()) {
                categories.setTemplates(position, viewAdapter.getData())
            }
            findNavController().navigateUp()
        }
        return root
    }

    class Adapter(private val templateList: List<FileList>, savedList: MutableList<UUID>) : RecyclerView.Adapter<ViewHolder>() {
        private val templateMap = TemplatesAsMap(templateList, savedList)
        private val data = templateMap.data

        fun isEmpty(): Boolean {
            return data.isEmpty()
        }

        override fun getItemCount() = if (isEmpty()) { 1 } else { data.size }

        override fun getItemViewType(position: Int): Int {
            return if (isEmpty()) { R.layout.empty_recycler } else { R.layout.list_manage_item }
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
                val checkBox = item.findViewById<CheckBox>(R.id.checkBox)!!
                checkBox.text = templateList[position].name
                setState(checkBox, position)
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    data[templateList[position]] = isChecked
                }
            }
        }

        private fun setState(checkBox: CheckBox, position: Int) {
            if (data[templateMap.getFromPosition(position)]!!) {
                checkBox.isChecked = true
            }
        }

        fun getData(): MutableList<UUID> {
            return templateMap.flatten()
        }
    }
}