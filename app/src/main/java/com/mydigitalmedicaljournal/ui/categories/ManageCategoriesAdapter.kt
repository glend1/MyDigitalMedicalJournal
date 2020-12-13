package com.mydigitalmedicaljournal.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.data.FileList
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import com.mydigitalmedicaljournal.template.data.TemplatesAsMap
import java.util.*
class ManageCategoriesAdapter(private val templateList: List<FileList>, savedList: MutableList<UUID>) : RecyclerView.Adapter<ViewHolder>() {
    private val templateMap = TemplatesAsMap(templateList, savedList)
    private val data = templateMap.data
    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_manage_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.checkBox)!!
        checkBox.text = templateList[position].name
        setState(checkBox, position)
        checkBox.setOnCheckedChangeListener{ _, isChecked ->
            data[templateList[position]] = isChecked
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
