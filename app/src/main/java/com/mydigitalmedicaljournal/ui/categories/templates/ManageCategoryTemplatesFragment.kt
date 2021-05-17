package com.mydigitalmedicaljournal.ui.categories.templates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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
import com.mydigitalmedicaljournal.ui._generics.EmptyAdapter
import java.util.*

class ManageCategoryTemplatesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val categories = Categories(requireContext())
        val position = arguments?.get("categoryPosition") as Int?
        val category = if (position != null) {categories.getCategory(position)} else {null}
        val root = inflater.inflate(R.layout.fragment_manage_templates, container, false)
        val viewAdapter = Adapter(TemplateList(requireContext()).get(), category?.templates)
        val templateList = root.findViewById<RecyclerView>(R.id.recycler)
        val itemDecoration = CustomDivider(requireContext())
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        val save = root.findViewById<ConstraintLayout>(R.id.save)
        save.setOnClickListener {
            if (!viewAdapter.isEmpty() && position != null) {
                categories.setTemplates(position, viewAdapter.getData()!!)
            }
            findNavController().navigateUp()
        }
        return root
    }

    class Adapter(private val templateList: List<FileList>, savedList: MutableList<UUID>?) : EmptyAdapter() {
        override val message = R.string.no_templates
        override val layout = R.layout.list_manage_item
        private val templateMap = if (savedList != null) {TemplatesAsMap(templateList, savedList)} else {null}
        private val data = templateMap?.data
        override fun getItemCount() = if (isEmpty()) { 1 } else { data!!.size }
        override fun isEmpty() = data?.isEmpty() ?: true
        fun getData() = templateMap?.flatten()

        private fun setState(checkBox: CheckBox, position: Int) {
            if (data!![templateMap!!.getFromPosition(position)]!!) {
                checkBox.isChecked = true
            }
        }

        override fun bindView(view: View, position: Int) {
            val checkBox = view.findViewById<CheckBox>(R.id.checkBox)!!
            checkBox.text = templateList[position].name
            setState(checkBox, position)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                data!![templateList[position]] = isChecked
            }
        }
    }
}