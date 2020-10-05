package com.mydigitalmedicaljournal.ui.journal.selector

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateSelectorAdapter(fileList: MutableList<TemplateList.NestedTemplates>) : RecyclerView.Adapter<ViewHolder>() {

    class CategoriesTemplate(val name:String, val type:CategoriesTemplateType)
    enum class CategoriesTemplateType { CATEGORY, TEMPLATE }
    private val flatList = mutableListOf<CategoriesTemplate>()

    init {
        for (category in fileList) {
            flatList.add(CategoriesTemplate(category.category, CategoriesTemplateType.CATEGORY))
            for (template in category.templates) {
                flatList.add(CategoriesTemplate(template.name, CategoriesTemplateType.TEMPLATE))
            }
        }

    }

    override fun getItemCount() = flatList.size

    override fun getItemViewType(position: Int) = flatList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = if (viewType == 0) {
            LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_child, parent, false)
        }
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = holder.itemView
        val title = item.findViewById<TextView>(R.id.text)
        title.text = flatList[position].name
        //TODO this needs finishing
        /*item.setOnClickListener {
            val bundle = bundleOf("data" to fileList[position].id)
            item.findNavController().navigate(R.id.editorFragment, bundle)
        }*/
    }
}