package com.mydigitalmedicaljournal.ui.templates.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.json.JsonData
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import java.util.*

class ManageCategoriesAdapter(var json: JsonData, private var savedList: MutableList<UUID>) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount() = json.data.size
    var localData = mutableListOf<UUID>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_manage_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.checkBox)
        checkBox.text = json.data[position].name
        setState(checkBox, position)
        checkBox.setOnCheckedChangeListener{ _, isChecked ->
            updateLocally(position, isChecked)
        }
    }

    private fun updateLocally(position: Int, checked: Boolean) {
        if (checked) {
            localData.add(json.data[position].id)
        } else {
            localData.remove(json.data[position].id)
        }
    }

    private fun setState(checkBox: CheckBox?, position: Int) {
        if (savedList.contains(json.data[position].id)) {
            updateLocally(position, true)
            checkBox!!.isChecked = true
        }
    }

}