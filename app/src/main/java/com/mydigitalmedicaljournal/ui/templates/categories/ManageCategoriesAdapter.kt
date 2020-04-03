package com.mydigitalmedicaljournal.ui.templates.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import java.io.File
import java.util.*
//TODO this shouldn't take a array of files, it should take an array of something that i haven't made yet.
class ManageCategoriesAdapter(val list: Array<File>, private var savedList: MutableList<UUID>) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount() = 0 //list.size
    var localData = mutableListOf<UUID>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_manage_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.checkBox)
        //checkBox.text = json.data[position].name
        setState(checkBox, position)
        checkBox.setOnCheckedChangeListener{ _, isChecked ->
            updateLocally(position, isChecked)
        }
    }

    private fun updateLocally(position: Int, checked: Boolean) {
        if (checked) {
            //localData.add(json.data[position].id)
        } else {
            //localData.remove(json.data[position].id)
        }
    }

    private fun setState(checkBox: CheckBox?, position: Int) {
        /*if (savedList.contains(json.data[position].id)) {
            updateLocally(position, true)
            checkBox!!.isChecked = true
        }*/
    }

}
