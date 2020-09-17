package com.mydigitalmedicaljournal.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import java.util.*
class ManageCategoriesAdapter(private val templateList: List<TemplateList.FileList>, private var savedList: MutableList<UUID>) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount() = templateList.size
    var localData = mutableListOf<UUID>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_manage_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.checkBox)
        checkBox.text = templateList[position].name
        setState(checkBox, position)
        checkBox.setOnCheckedChangeListener{ _, isChecked ->
            updateLocally(position, isChecked)
        }
    }

    private fun updateLocally(position: Int, checked: Boolean) {
        if (checked) {
            localData.add(templateList[position].id)
        } else {
            localData.remove(templateList[position].id)
        }
    }

    private fun setState(checkBox: CheckBox?, position: Int) {
        if (savedList.contains(templateList[position].id)) {
            updateLocally(position, true)
            checkBox!!.isChecked = true
        }
    }

}
