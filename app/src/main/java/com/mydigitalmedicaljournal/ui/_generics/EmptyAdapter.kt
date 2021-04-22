package com.mydigitalmedicaljournal.ui._generics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R

abstract class EmptyAdapter : RecyclerView.Adapter<ViewHolder>() {
    abstract val message: Int
    abstract val layout: Int
    abstract override fun getItemCount(): Int
    abstract fun isEmpty(): Boolean
    abstract fun bindView(view: View, position: Int)
    override fun getItemViewType(position: Int) = if (isEmpty()) { R.layout.empty_recycler } else { layout }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        if (isEmpty()) {
            view.findViewById<TextView>(R.id.message).text = view.resources.getString(message)
        } else {
            bindView(view, position)
        }
    }

}