package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class EditorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //TODO complete this adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //TODO i need a list of applicable view types to pull from
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.editor_add, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //TODO this needs to use the view type
        holder.itemView.findViewById<TextView>(R.id.text).text = holder.itemView.context.getString(R.string.add_field)
        holder.itemView.setOnClickListener {
            //TODO set this action
            Log.i("ADD", "New field")
        }
    }

    override fun getItemCount(): Int = 1

}
