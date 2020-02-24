package com.mydigitalmedicaljournal.ui.generics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.json.JsonData

abstract class ManageableListAdapter(open val json: JsonData, val layout: Int) : RecyclerView.Adapter<ManageableListAdapter.ViewHolder>() {

    override fun getItemCount() = json.data.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = holder.itemView.findViewById<TextView>(R.id.title)
        title.text = json.data[position].name
        bindEvents(holder, position)
    }

    abstract fun bindEvents(holder: ViewHolder, position: Int)
}